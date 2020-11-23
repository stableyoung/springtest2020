package com.mycompany.testproj.domain;


import java.io.File;
import java.io.IOException;
import java.util.List;

import com.mycompany.testproj.util.Utils;



import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UploadedFile {

    private static final Logger logger = LoggerFactory.getLogger(UploadedFile.class);

    @SuppressWarnings("unused")
    private static final long serialVersionUID = -7373826970792639261L;

    String serverFileName;
    String originalFileName;
    String contentType;
    long size;
    String url;
    String gubun; //구현화면에 따라 필요시 사용하는 필드
    String etc; //구현화면에 따라 필요시 사용하는 필드

    String subPath; //모듈별 sub directory

    File repositoryFile; //최종 저장 디렉토리 저장된 파일 객체

    List<UploadedFile> uploadedFiles;

    public List<UploadedFile> getUploadedFiles() {
        return uploadedFiles;
    }
    public void setUploadedFiles(List<UploadedFile> uploadedFiles) {
        this.uploadedFiles = uploadedFiles;
    }

    public File getRepositoryFile() {
        return repositoryFile;
    }
    public void setRepositoryFile(File repositoryFile) {
        this.repositoryFile = repositoryFile;
    }
    public String getServerFileName() {
        return serverFileName;
    }
    public void setServerFileName(String serverFileName) {
        this.serverFileName = serverFileName;
    }
    public String getOriginalFileName() {
        return originalFileName;
    }
    public void setOriginalFileName(String originalFileName) {
        this.originalFileName = originalFileName;
    }

    public long getSize() {
        return size;
    }
    public void setSize(long size) {
        this.size = size;
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public String getSubPath() {
        return subPath;
    }
    public void setSubPath(String subPath) {
        this.subPath = subPath;
    }
    public String getContentType() {
        return contentType;
    }
    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getGubun() {
        return gubun;
    }
    public void setGubun(String gubun) {
        this.gubun = gubun;
    }
    public String getEtc() {
        return etc;
    }
    public void setEtc(String etc) {
        this.etc = etc;
    }
    /**
     * 최종 저장 디렉토리에 저장된 파일 삭제
     */
    public void deleteRepositoryFile(){
        try{
            FileUtils.forceDelete(this.getRepositoryFile());
        }catch(IOException e){
            logger.error(Utils.getMethodName(), e);
        }catch(Exception e){
            logger.error(Utils.getMethodName(), e);
        }
    }

    public String toString() {
        return ToStringBuilder.reflectionToString(this,	ToStringStyle.MULTI_LINE_STYLE);
    }
    public boolean equals(Object o) {
        return EqualsBuilder.reflectionEquals(this, o);
    }
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }
}
