             package com.example.demo.dto;
//
//
//                public class FileMeetupDTO {
//                    private Long id;
//                    private String typeFile;
//                   // private String url_file;
//                    private byte[] file;
//
//                    //השם של קובץ הPDF
//                    private String name;
//
//                    private Long gallery_category_id;
//
//
//                    //get&&set
//                    public Long getGallery_category_id() {
//                        return gallery_category_id;
//                    }
//
//                    public void setGallery_category_id(Long gallery_category_id) {
//                        this.gallery_category_id = gallery_category_id;
//                    }
//
//                    public Long getId() {
//                        return id;
//                    }
//
//                    public void setId(Long id) {
//                        this.id = id;
//                    }
//
//                    public String getName() {
//                        return name;
//                    }
//
//                    public void setName(String name) {
//                        this.name = name;
//                    }
//
//                    public String getTypeFile() {
//                        return typeFile;
//                    }
//
//                    public void setTypeFile(String typeFile) {
//                        this.typeFile = typeFile;
//                    }
//
////                    public String getUrl_file() {
////                        return url_file;
////                    }
////
////                    public void setUrl_file(String url_file) {
////                        this.url_file = url_file;
////                    }
//
//                    public byte[] getFile() {
//                        return file;
//                    }
//
//                    public void setFile(byte[] file) {
//                        this.file = file;
//                    }
//                }


//GPT

//public class FileMeetupDTO {
//
//    private Long id;
//    private String name;
//    private String typeFile;  // סוג הקובץ (למשל תמונה, וידאו)
//    private String url_file;      // בשלב ההוספה: נתיב או URL של הקובץ
//    private byte[] fileData;  // בשלב השליפה: נתוני הקובץ בביטים
//    private Long gallery_category_id;
//
//    // get & set methods
//
//    public byte[] getFileData() {
//        return fileData;
//    }
//
//    public void setFileData(byte[] fileData) {
//        this.fileData = fileData;
//    }
//
//    public Long getGallery_category_id() {
//        return gallery_category_id;
//    }
//
//    public void setGallery_category_id(Long gallery_category_id) {
//        this.gallery_category_id = gallery_category_id;
//    }
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getTypeFile() {
//        return typeFile;
//    }
//
//    public void setTypeFile(String typeFile) {
//        this.typeFile = typeFile;
//    }
//
//    public String getUrl_file() {
//        return url_file;
//    }
//
//    public void setUrl_file(String url_file) {
//        this.url_file = url_file;
//    }
//}

//GPT
//בשביל התמונות והוידיו
public class FileMeetupDTO {

    private Long id;
    private String name;
    private String typeFile;  // סוג הקובץ (תמונה, וידאו)
    private String url_file;      // URL של הקובץ (אם מדובר בסרטון)
    private byte[] fileData;  // נתוני הקובץ בביטים (אם מדובר בתמונה)
    private Long gallery_category_id;
  //  private String fileType; // "image" או "video" (לזהות את סוג הקובץ)

    // get & set methods

    public byte[] getFileData() {
        return fileData;
    }

    public void setFileData(byte[] fileData) {
        this.fileData = fileData;
    }

    public Long getGallery_category_id() {
        return gallery_category_id;
    }

    public void setGallery_category_id(Long gallery_category_id) {
        this.gallery_category_id = gallery_category_id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTypeFile() {
        return typeFile;
    }

    public void setTypeFile(String typeFile) {
        this.typeFile = typeFile;
    }

    public String getUrl_file() {
        return url_file;
    }

    public void setUrl_file(String url_file) {
        this.url_file = url_file;
    }

//    public String getFileType() {
//        return fileType;
//    }
//
//    public void setFileType(String fileType) {
//        this.fileType = fileType;
//    }
}

