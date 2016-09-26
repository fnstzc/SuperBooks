package com.zc.superbooks.controller;

	import java.io.BufferedReader;
	import java.io.File;
	import java.io.FileInputStream;
	import java.io.FileNotFoundException;
	import java.io.FileOutputStream;
	import java.io.IOException;
	import java.io.InputStream;
	import java.io.InputStreamReader;
	import java.io.OutputStream;
	import java.io.StringReader;
	import java.nio.charset.Charset;
	import java.nio.file.Path;
	import java.nio.file.Paths;
	import java.util.ArrayList;
	import java.util.Date;
	import java.util.Enumeration;
	import java.util.HashMap;
import java.util.Iterator;
	import java.util.LinkedList;
	import java.util.List;
	import java.util.Map;
	import java.util.Queue;
	import java.util.zip.ZipEntry;
	import java.util.zip.ZipInputStream;

	import javax.xml.bind.DataBindingException;
	import javax.xml.bind.JAXB;

	import org.apache.commons.compress.archivers.zip.ZipFile;
	import org.apache.log4j.Logger;

	import com.fujitsu.proasq.dita.api.entity.constant.FileType;
	import com.fujitsu.proasq.dita.api.entity.model.Cover;
	import com.fujitsu.proasq.dita.api.entity.model.FileInfo;
	import com.fujitsu.proasq.dita.api.entity.model.Filter;
	import com.fujitsu.proasq.dita.api.entity.model.Manual;
	import com.fujitsu.proasq.dita.api.entity.model.ManualInfo;
	import com.fujitsu.proasq.dita.api.entity.model.ResourceInfo;
	import com.fujitsu.proasq.dita.api.entity.xml.CoverToXml;
	import com.fujitsu.proasq.dita.api.entity.xml.ManualToXml;
	import com.fujitsu.proasq.dita.api.entity.xml.Prop;
	import com.fujitsu.proasq.dita.api.entity.xml.ValToXml;
	import com.fujitsu.proasq.dita.data.exception.DataMgrDBException;
	import com.fujitsu.proasq.dita.data.exception.DataMgrFileInvalid;
	import com.fujitsu.proasq.dita.data.exception.DataMgrFileManipulateException;
	import com.fujitsu.proasq.dita.data.exception.DataMgrManualFileNotFound;
	import com.fujitsu.proasq.dita.data.exception.DataMgrResourceFileNotFound;
	import com.fujitsu.proasq.dita.data.exception.DataMgrResourceIsUsing;
	import com.fujitsu.proasq.dita.data.exception.DataMgrResourceNotAccessible;
	import com.fujitsu.proasq.dita.data.exception.DataMgrResourceNotFound;
	import com.fujitsu.proasq.dita.db.bean.table.Resource;
	import com.fujitsu.proasq.dita.db.exception.DBException;
	import com.fujitsu.proasq.dita.db.exception.EntityIsUsingException;
	import com.fujitsu.proasq.dita.db.exception.EntityNotFoundException;
	import com.fujitsu.proasq.dita.db.jpa.handler.ActionHandler;
import com.fujitsu.proasq.dita.db.jpa.handler.ResourceHandler;

	public class ResourceManager {
		public static final String RESOURCE_SUFFIX = ".zip";

		private static final Logger LOGGER = Logger.getLogger(ResourceManager.class);

		private static final String ManualFileName = "manual.xml";
		private String dbUnit;

		public ResourceManager(String dbUnit) {
			this.dbUnit = dbUnit;
		}

		public Resource getResource(int resourceId) throws DataMgrDBException{
			Resource resource = null;
			try {
				ResourceHandler resourceHandler = new ResourceHandler(dbUnit);
				resource = resourceHandler.findById(resourceId);
			} catch (Exception e) {
				throw new DataMgrDBException(e.getMessage(), e.getCause());
			}
			return resource;
		}
		
		public List<Integer> getResourceList(String userId, String projectId)
				throws DataMgrDBException {
			List<Integer> result = null;
			try {
				ResourceHandler resourceHandler = new ResourceHandler(dbUnit);
				result = resourceHandler.getResourceList(userId, projectId);
			} catch (Exception e) {
				throw new DataMgrDBException(e.getMessage(), e.getCause());
			}
			return result;
		}

		public int registerResource(String resourceHome, Resource resource, InputStream inputStream) throws DataMgrDBException, IOException, DataMgrFileManipulateException {
			File dest = null;
			ResourceHandler resourceHandler = new ResourceHandler(dbUnit);
			try {
				resourceHandler.setTarget(resource);
				resourceHandler.create();
			} catch (Throwable e) {
				throw new DataMgrDBException(e.getMessage(), e.getCause());
			}

			try {
				dest = new File(resourceHome, resource.getId() + RESOURCE_SUFFIX);
				saveFile(inputStream, dest);
			} catch (IOException e) {
				if (dest != null && dest.exists()) {
					if (!dest.delete()) {
						throw new DataMgrFileManipulateException(
								"File can not delete.");
					}
				}

				try {
					Resource _t = resourceHandler.findById(resource.getId());
					resourceHandler.setTarget(_t);
					resourceHandler.delete();
				} catch (Throwable nae) {
					LOGGER.error(nae.getMessage(), nae);
				}
				throw e;
			}
			return resource.getId();
		}

		protected void saveFile(InputStream inputStream, File dest)
				throws IOException {
			OutputStream outputStream = null;
			try {
				outputStream = new FileOutputStream(dest);
				int length = 0;
				byte[] bytes = new byte[1024];
				while ((length = inputStream.read(bytes)) != -1) {
					outputStream.write(bytes, 0, length);
				}
				outputStream.flush();
			} finally {
				if (outputStream != null) {
					try {
						outputStream.close();
					} catch (IOException e) {
						LOGGER.error(e.getMessage(), e);
					}
				}
			}
		}

		public File getResourceZipFile(String resourceHome, int resourceId, String userId)
				throws DataMgrDBException, DataMgrResourceFileNotFound, DataMgrResourceNotFound, DataMgrResourceNotAccessible {
			Resource resource = null;
			File resourceZipFile = null;
			try {
				ResourceHandler resourceHandler = new ResourceHandler(dbUnit);
				resource = resourceHandler.findById(resourceId);
				if (null == resource || resource.getDeletedTime() != null) {
					throw new DataMgrResourceNotFound(resourceId);
				}
				if (!userId.equals(resource.getUserId())) {
					throw new DataMgrResourceNotAccessible(resourceId);
				}
				String resourceZipFileName = Integer.toString(resourceId)
						+ RESOURCE_SUFFIX;
				resourceZipFile = new File(resourceHome, resourceZipFileName);
				if (!resourceZipFile.isFile()) {
					throw new DataMgrResourceFileNotFound(resourceZipFileName);
				}
			} catch (IllegalArgumentException e) {
				throw new DataMgrDBException(e.getMessage(), e.getCause());
			}

			return resourceZipFile;
		}

		public void remove(String resourceHome, Resource resource) throws DataMgrDBException {
			try {
				ResourceHandler resourceHandler = new ResourceHandler(dbUnit);
				resourceHandler.setTarget(resource);
				resourceHandler.delete();
				String resourceZipFileName = resource.getId() + RESOURCE_SUFFIX;
				File resourceZipFile = new File(resourceHome, resourceZipFileName);
				if (!resourceZipFile.delete()) {
					throw new RuntimeException("Failed to delete file:[" + resourceZipFile.getAbsolutePath() + "]");
				}
			} catch (Throwable e) {
				throw new DataMgrDBException(e.getMessage(), e.getCause());
			}
		}

		public ResourceInfo getResourceInfo(int resourceId, String userId) throws DataMgrDBException, DataMgrResourceNotFound, DataMgrResourceNotAccessible {
			Resource resource = null;
			try {
				ResourceHandler resourceHandler = new ResourceHandler(dbUnit);
				resource = resourceHandler.findById(resourceId);
			} catch (Exception e) {
				throw new DataMgrDBException(e.getMessage(), e.getCause());
			}
			if (resource == null || resource.getDeletedTime() != null) {
				throw new DataMgrResourceNotFound(resourceId);
			}
			if (!userId.equals(resource.getUserId())) {
				throw new DataMgrResourceNotAccessible(resourceId);
			}
			ResourceInfo resourceInfo = new ResourceInfo();
			wrapResourceInfo(resourceInfo, resource);
			try {
				ActionHandler actionHandler = new ActionHandler(dbUnit);
				boolean isUsing = actionHandler.isResourceUsing(resourceId);
				if (isUsing) {
					resourceInfo.setResourceStatus(ResourceInfo.USEING);
				} else {
					resourceInfo.setResourceStatus(ResourceInfo.UNUSED);
				}
			} catch (Exception e) {
				throw new DataMgrDBException(e.getMessage(), e.getCause());
			}
			return resourceInfo;
		}

		private void wrapResourceInfo(ResourceInfo resourceInfo, Resource resource) {
			resourceInfo.setDescription(resource.getDescription());
			resourceInfo.setProjectId(resource.getProjectId());
			resourceInfo.setResourceId(resource.getId());
			resourceInfo.setResourceName(resource.getName());
			resourceInfo.setUploadTime(resource.getUploadTime());
			resourceInfo.setUserId(resource.getUserId());
		}

		public FileInfo getResourceFileInfo(String resourceHome, int resourceId, String userId) throws DataMgrDBException, DataMgrResourceFileNotFound, IOException, DataMgrResourceNotFound, DataMgrResourceNotAccessible {
			File resourceFile = getResourceZipFile(resourceHome, resourceId, userId);
			if (resourceFile == null) {
				return null;
			}
			ZipFile zipFile = null;
			FileInfo resourceFileInfo = null;
			try {
				zipFile = new ZipFile(resourceFile, "MS932");
				Enumeration<? extends ZipEntry> zipEntries = zipFile.getEntries();
				Map<String, FileInfo> fileInfoMap = new HashMap<String, FileInfo>();
				resourceFileInfo = new FileInfo();
				resourceFileInfo.setName("ROOT");
				resourceFileInfo.setType(FileType.directory);
				fileInfoMap.put("/", resourceFileInfo);
				Queue<ZipEntry> fileQueue = new LinkedList<ZipEntry>();
				while (zipEntries.hasMoreElements()) {
					ZipEntry curEntry = zipEntries.nextElement();
					FileType type = (curEntry.isDirectory() ? FileType.directory : FileType.file);
					if (type.equals(FileType.directory)) {
						createFileInfo(fileInfoMap, curEntry.getName(), null, type);
					} else {
						fileQueue.offer(curEntry);
					}
				}
				while (!fileQueue.isEmpty()) {
					createFileInfo(fileInfoMap, fileQueue.poll().getName(), null, FileType.file);
				}
			} finally {
				if (null != zipFile) {
					try {
						zipFile.close();
					} catch (IOException e) {
						LOGGER.error(e.getMessage(), e);
					}
				}
			}
			return resourceFileInfo;
		}

		public void deleteResource(int resourceId, String userId) throws DataMgrResourceIsUsing,
				DataMgrResourceNotFound, DataMgrDBException, DataMgrResourceNotAccessible {
			try {
				ResourceHandler resourceHandler = new ResourceHandler(dbUnit);
				Resource resource = resourceHandler.findById(resourceId);
				if (resource == null || resource.getDeletedTime() != null) {
					throw new DataMgrResourceNotFound(resourceId);
				}
				if (!userId.equals(resource.getUserId())) {
					throw new DataMgrResourceNotAccessible(resourceId);
				}
				ActionHandler actionHandler = new ActionHandler(dbUnit);
				boolean isUsing = actionHandler.isResourceUsing(resourceId);
				if (isUsing) {
					throw new DataMgrResourceIsUsing(resourceId);
				}
				resourceHandler.deleteResource(resourceId);
			} catch (EntityIsUsingException e) {
				throw new DataMgrResourceIsUsing(resourceId);
			} catch (EntityNotFoundException e) {
				throw new DataMgrResourceNotFound(resourceId);
			} catch (DBException e) {
				throw new DataMgrDBException(e.getMessage(), e.getCause());
			}
		}

		private void createFileInfo(Map<String, FileInfo> fileInfoMap,
				String namePath, FileInfo childFileInfo, FileType type) {
			if (namePath.endsWith("/")) {
				namePath = namePath.substring(0, namePath.length() - 1);
			}

			// current directory is in map
			if (fileInfoMap.containsKey(namePath)) {
				FileInfo curFileInfo = fileInfoMap.get(namePath);
				// directory create children list
				if (type == FileType.directory) {
					if (null == curFileInfo.getChildren()) {
						curFileInfo.setChildren(new ArrayList<FileInfo>());
					}
					// append all childs to current fileInfo
					if (null != childFileInfo) {
						curFileInfo.getChildren().add(childFileInfo);
					}
				}
			} else {
				String[] nameArray = namePath.split("/");
				FileInfo curFileInfo = new FileInfo();
				String fileName = nameArray[nameArray.length - 1];
				curFileInfo.setType(type);
				curFileInfo.setName(fileName);
				// directory create children list
				if (type == FileType.directory) {
					if (null == curFileInfo.getChildren()) {
						curFileInfo.setChildren(new ArrayList<FileInfo>());
					}
					// append all children to current fileInfo
					if (null != childFileInfo) {
						curFileInfo.getChildren().add(childFileInfo);
					}
					// put directory into map
					fileInfoMap.put(namePath, curFileInfo);
				}
				// if length = 1, parentKey is root, here is "//" because when namePath.endsWith("/")
				//  last "/" will be removed
				String parentKey = "//";
				if (nameArray.length > 1) {
					parentKey = namePath.substring(0, namePath.length() - fileName.length());
				}
				createFileInfo(fileInfoMap, parentKey, curFileInfo, FileType.directory);
			}
		}

		/*
		 * マニュアル情報を取得する。
		 * @param zipFile 原稿ファイル
		 * @param targetFolder フォルダディレクトリ
		 * @param manual マニュアル情報
		 * @return result (false：XML形式になっていない場合; true：その他)
		 * @exception DataMgrFileManipulateException, FileNotFoundException
		 */
//		public boolean getManualData(File zipFile, String targetFolder, Manual manual) throws DataMgrFileManipulateException,
//				FileNotFoundException, DataMgrManualFileNotFound, DataMgrFileInvalid {
//			boolean result = true;
//			StringReader stringReader = null;
//			try {
//				String readFileDataFromZip = readFileDataFromZip(zipFile, Paths.get(targetFolder, ManualFileName).normalize());
//				if (readFileDataFromZip.isEmpty()) {
//					throw new DataMgrManualFileNotFound("manual file not found");
//				}
//				stringReader = new StringReader(readFileDataFromZip);
//				ManualToXml manualData = null;
//				try {
//					manualData = JAXB.unmarshal(stringReader, ManualToXml.class);
//				} catch (DataBindingException e) {
//					// manual.xmlはXML形式になっていない場合
//					throw new DataMgrFileInvalid("manual file invalid");
//				}
	//
//				boolean coverParseFlag = true;
//				Cover coverData = null;
//				try {
//					coverData = getCoverData(zipFile, manualData.getCoverFile(), targetFolder);
//				} catch (DataMgrResourceFileNotFound e) {
//					coverData = null;
//				} catch (DataBindingException e) {
//					// cover.xmlはXML形式になっていない場合
//					coverData = null;
//					coverParseFlag = false;
//				}
//				
//				boolean filterParseFlag = true;
//				List<Filter> filterList = null;
//				try {
//					filterList = getFilterData(zipFile, manualData.getFilterFile(), targetFolder);
//				} catch (DataMgrResourceFileNotFound e) {
//					filterList = null;
//				} catch (DataBindingException e) {
//					// filter.ditavalはXML形式になっていない場合
//					filterList = null;
//					filterParseFlag = false;
//				}
	//
//				if (!coverParseFlag && !filterParseFlag) {
//					// cover.xmlとfilter.ditavalはXML形式になっていない場合
//					result = false;
//				} else if(!coverParseFlag) {
//					// cover.xmlはXML形式になっていないの場合
//					result = false;
//					if (filterList == null) {
//						filterList = new ArrayList<Filter>();
//					}
//				} else if (!filterParseFlag) {
//					// filter.ditavalはXML形式になっていない場合
//					result = false;
//					if (coverData == null) {
//						coverData = new Cover();
//					}
//				}
//				
//				//dita-api-entityに手を入られないため、tempのmanual bean を定義する
//				Manual tempManual = new Manual(manualData, coverData, filterList);
//				setManualData(manual, tempManual);
//			} catch (FileNotFoundException e) {//ZIPファイルがない場合
//				throw e;
//			} catch (IOException e) {
//				throw new DataMgrFileManipulateException(zipFile.getAbsolutePath());
//			} finally {
//				if (stringReader != null) {
//					stringReader.close();
//				}
//			}
//			return result;
//		}
		
		public boolean getManualData(File zipFile, String targetFolder,
				Manual manual) throws DataMgrFileManipulateException,
				FileNotFoundException, DataMgrManualFileNotFound,
				DataMgrFileInvalid {
			boolean result = true;
			StringReader stringReader = null;
			try {
				String readFileDataFromZip = readFileDataFromZip(zipFile, Paths.get(targetFolder, ManualFileName).normalize());
				if (readFileDataFromZip.isEmpty()) {
					throw new DataMgrManualFileNotFound("manual file not found");
				}
				stringReader = new StringReader(readFileDataFromZip);
				ManualToXml manualData = null;
				try {
					manualData = JAXB.unmarshal(stringReader, ManualToXml.class);
				} catch (DataBindingException e) {
					// manual.xmlはXML形式になっていない場合
					throw new DataMgrFileInvalid("manual file invalid");
				}

				boolean coverParseFlag = true;
				boolean filterParseFlag = true;
				
				Cover coverData = null;
				List<Filter> filterList = null;
				
				if (manualData.getCoverFile() == null) {
					coverData = new Cover();
				}
				if (manualData.getFilterFile() == null) {
					filterList = new ArrayList<Filter>();
				}
				
				try {
					getCoverAndFilterData(zipFile, coverData, filterList, manualData.getCoverFile(), manualData.getFilterFile(),targetFolder);
				} catch (DataMgrResourceFileNotFound e) {
					coverData = null;
				} catch (DataBindingException e) {
					// cover.xmlはXML形式になっていない場合
					coverData = null;
					coverParseFlag = false;
				}
				
				try {
					filterList = getFilterData(zipFile, manualData.getFilterFile(),
							targetFolder);
				} catch (DataMgrResourceFileNotFound e) {
					filterList = null;
				} catch (DataBindingException e) {
					// filter.ditavalはXML形式になっていない場合
					filterList = null;
					filterParseFlag = false;
				}

				if (!coverParseFlag && !filterParseFlag) {
					// cover.xmlとfilter.ditavalはXML形式になっていない場合
					result = false;
				} else if (!coverParseFlag) {
					// cover.xmlはXML形式になっていないの場合
					result = false;
					if (filterList == null) {
						filterList = new ArrayList<Filter>();
					}
				} else if (!filterParseFlag) {
					// filter.ditavalはXML形式になっていない場合
					result = false;
					if (coverData == null) {
						coverData = new Cover();
					}
				}

				// dita-api-entityに手を入られないため、tempのmanual bean を定義する
				Manual tempManual = new Manual(manualData, coverData, filterList);
				setManualData(manual, tempManual);
			} catch (FileNotFoundException e) {// ZIPファイルがない場合
				throw e;
			} catch (IOException e) {
				throw new DataMgrFileManipulateException(zipFile.getAbsolutePath());
			} finally {
				if (stringReader != null) {
					stringReader.close();
				}
			}
			return result;
		}
		
		private void setManualData(Manual manual, Manual tempManual) {
			manual.setMapPath(tempManual.getMapPath());
			manual.setCover(tempManual.getCover());
			manual.setFilter(tempManual.getFilter());
			manual.setPdfPassword(tempManual.getPdfPassword());
			manual.setShowImageFilePath(tempManual.getShowImageFilePath());
			manual.setShowTopicFilePath(tempManual.getShowTopicFilePath());
			manual.setGenerateTaskLabel(tempManual.getGenerateTaskLabel());
		}

		private Cover getCoverData(File zipFile, String coverFileName, String targetFolder) 
				throws DataMgrResourceFileNotFound,
				FileNotFoundException, IOException {

			StringReader stringReader = null;
			try {
				if (coverFileName == null) {
					return new Cover();
				}

				String readFileDataFromZip = readFileDataFromZip(zipFile, Paths.get(targetFolder, coverFileName).normalize());
				if (readFileDataFromZip.isEmpty()) {
					throw new DataMgrResourceFileNotFound(coverFileName);
				}
				stringReader = new StringReader(readFileDataFromZip);
				CoverToXml coverData = JAXB.unmarshal(stringReader, CoverToXml.class);
				return new Cover(coverData);
			} finally {
				if (stringReader != null) {
					stringReader.close();
				}
			}
		}

		private List<Filter> getFilterData(File zipFile, String filterFileName,
				String targetFolder) throws DataMgrResourceFileNotFound,
				FileNotFoundException, IOException {
			StringReader stringReader = null;
			List<Filter> result = new ArrayList<Filter>();
			try {
				if (filterFileName == null) {
					return result;
				}
				
				String readFileDataFromZip = readFileDataFromZip(zipFile, Paths.get(targetFolder, filterFileName).normalize());
				if (readFileDataFromZip.isEmpty()) {
					throw new DataMgrResourceFileNotFound(filterFileName);
				}
				stringReader = new StringReader(readFileDataFromZip);
				ValToXml valData = JAXB.unmarshal(stringReader, ValToXml.class);
				for (Prop filterData : valData.getProp()) {
					result.add(new Filter(filterData));
				}
				return result;
			} finally {
				if (stringReader != null) {
					stringReader.close();
				}
			}
		}

		public void getCoverAndFilterData(File zipFile, Cover cover, List<Filter> filterList, String filterFileName,String coverFileName,
				String targetFolder) throws DataMgrResourceFileNotFound,
				FileNotFoundException, IOException {
			
			StringReader coverStringReader = null;
			StringReader filterStringReader = null;
			
			List<Path> filePaths = new ArrayList<Path>();
			
			Path coverFilePath = Paths.get(targetFolder, coverFileName).normalize();
			Path filterFilePath = Paths.get(targetFolder,filterFileName).normalize();
			
			filePaths.add(coverFilePath);
			filePaths.add(filterFilePath);
			
			try {
				HashMap<String, String> fileDataStrMap = readCoverAndFilterFileDataFromZip(zipFile, filePaths);
				String coverDataStr = fileDataStrMap.get(coverFilePath.getFileName());
				String filterDataStr = fileDataStrMap.get(filterFilePath.getFileName());
				
				if (coverDataStr.isEmpty()) {
					throw new DataMgrResourceFileNotFound(coverFileName);
				}
				if (filterDataStr.isEmpty()) {
					throw new DataMgrResourceFileNotFound(filterFileName);
				}
				
				coverStringReader = new StringReader(coverDataStr);
				CoverToXml coverData = JAXB.unmarshal(coverStringReader, CoverToXml.class);
				cover = new Cover(coverData);
				
				filterStringReader = new StringReader(filterDataStr);
				ValToXml valData = JAXB.unmarshal(filterStringReader, ValToXml.class);
				for (Prop filterData : valData.getProp()) {
					filterList.add(new Filter(filterData));
				}
			} finally {
				if (coverStringReader != null) {
					coverStringReader.close();
				}
				if (filterStringReader != null) {
					filterStringReader.close();
				}
			}
		}

		private HashMap<String, String> readCoverAndFilterFileDataFromZip(File zipFile, List<Path> filePaths) throws FileNotFoundException, IOException {
			HashMap<String, String> fileData = new HashMap<String, String>();
			StringBuilder coverDataStr = new StringBuilder();
			StringBuilder filterDataStr = new StringBuilder();
			StringBuilder dataStr = new StringBuilder();
			String readed;
			ZipInputStream zipInputStream = null;
			BufferedReader bufferReader = null;
			InputStreamReader inputStreamReader = null;
			
			try {
				zipInputStream = new ZipInputStream(new FileInputStream(zipFile.getAbsolutePath()), Charset.forName("MS932"));
				ZipEntry zipEntry;
				inputStreamReader = new InputStreamReader(zipInputStream);
				bufferReader = new BufferedReader(inputStreamReader);
				while ((zipEntry = zipInputStream.getNextEntry()) != null) {
					String name = zipEntry.getName();
					Path zipContentFile = Paths.get(name);

					Iterator<Path> iterator = filePaths.iterator();
					while(iterator.hasNext()){
			            Path path = iterator.next();
			            if (zipContentFile.compareTo(path) == 0) {
							while ((readed = bufferReader.readLine()) != null) {
								dataStr.append(readed).append("\n");
							}
							fileData.put(path.getFileName().toString(), dataStr.toString());
							iterator.remove();
							dataStr.delete(0, dataStr.length());
						}
					}
					if (filePaths.isEmpty()) {
						break;
					}
					zipInputStream.closeEntry();
				}
			} finally {
				if (bufferReader != null) {
					bufferReader.close();
				}
				if (inputStreamReader != null) {
					inputStreamReader.close();
				}
				if (zipInputStream != null) {
					zipInputStream.close();
				}
			}
			return fileData;
		}

		 private String readFileDataFromZip(File zipFile, Path targetFile) throws FileNotFoundException, IOException {
		 	StringBuilder dataStr = new StringBuilder();
		 	String readed;
		 	ZipInputStream zipInputStream = null;
		 	BufferedReader bufferReader = null;
		 	InputStreamReader inputStreamReader = null;
		 	try {
		 		zipInputStream = new ZipInputStream(new FileInputStream(zipFile.getAbsolutePath()), Charset.forName("MS932"));
		 		ZipEntry zipEntry;
		 		inputStreamReader = new InputStreamReader(zipInputStream);
		 		bufferReader = new BufferedReader(inputStreamReader);
		 		while ((zipEntry = zipInputStream.getNextEntry()) != null) {
		 			String name = zipEntry.getName();
		 			Path zipContentFile = Paths.get(name);
		 			if (zipContentFile.compareTo(targetFile) == 0) {
		 				while ((readed = bufferReader.readLine()) != null) {
		 					dataStr.append(readed).append("\n");
		 				}
		 				break;
		 			}
		 			zipInputStream.closeEntry();
		 		}
		 	} finally {
		 		if (bufferReader != null) {
		 			bufferReader.close();
		 		}
		 		if (inputStreamReader != null) {
		 			inputStreamReader.close();
		 		}
		 		if (zipInputStream != null) {
		 			zipInputStream.close();
		 		}
		 	}
		 	return dataStr.toString();
		 }

		public boolean getResourceManualInfo(String resourceHome, int resourceId,
				String userId, String targetFolder, ManualInfo manualInfo)
				throws DataMgrDBException, DataMgrResourceFileNotFound,
				DataMgrResourceNotFound, DataMgrFileManipulateException,
				FileNotFoundException, DataMgrResourceNotAccessible {
			boolean result = true;
			File zipFile = getResourceZipFile(resourceHome, resourceId, userId);
			Manual manual = new Manual();
			try {
				result = getManualData(zipFile, targetFolder, manual);
			} catch (DataMgrManualFileNotFound e) {
				manual = null;
				result = true;
			} catch (DataMgrFileInvalid e) {
				manual = null;
				result = false;
			}
			manualInfo.setManual(manual);
			return result;
		}
		
		public int setDeleteTimeForExpiredUploadedResouces(Date uploadedExpiredTime, Date deletedTime) {
			return new ResourceHandler(dbUnit).setDeleteTimeForExpiredUploadedResouces(uploadedExpiredTime, deletedTime);
		}

		public List<Integer> getExpiredDeletedResources(Date diskExpiredTime){
			return new ResourceHandler(dbUnit).getExpiredDeletedResources(diskExpiredTime);
		}
		
		public int updateExpiredDeletedResources(List<Integer> resourceIds){
			return new ResourceHandler(dbUnit).updateExpiredDeletedResources(resourceIds);
		}
		

		public void deleteResourceFile(String resourceHome, int resourceId)
				throws DataMgrDBException, DataMgrResourceFileNotFound {
			File resourceZipFile = null;
			String resourceZipFileName = resourceId + RESOURCE_SUFFIX;
			resourceZipFile = new File(resourceHome, resourceZipFileName);
			if (resourceZipFile.exists() && !resourceZipFile.delete()) {
					throw new DataMgrDBException("Failed to delete ResourceFile" + "["+ resourceZipFile.getName() +"]");
			}
			
		}

		public long getResourcesSize(String resourceHome, String resourceIds, String userId) throws DataMgrDBException {
			Resource resource = null;
			File resourceZipFile = null;
			long totalResourcesSize = 0;
			ResourceHandler resourceHandler = new ResourceHandler(dbUnit);
			String[] resourceIdStr = resourceIds.split(",");
			try {
				for (String resourceId : resourceIdStr) {
					resource = resourceHandler.findById(Integer.parseInt(resourceId));
					if(resource != null && resource.getDeletedTime() == null 
							&& userId.equals(resource.getUserId())) {
						String resourceZipFileName = resourceId + RESOURCE_SUFFIX;
						resourceZipFile = new File(resourceHome, resourceZipFileName);
						totalResourcesSize += resourceZipFile.length();
					}
				}
			} catch (IllegalArgumentException e) {
				throw new DataMgrDBException(e.getMessage(), e.getCause());
			}
			return totalResourcesSize;
		}
	}
}
