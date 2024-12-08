                package com.example.demo.dto;


                public class FileMeetupDTO {
                    private Long id;
                    private String typeFile;
                    private String url_file;
                    //השם של קובץ הPDF
                    private String name;
                    private Long gallery_category_id;


                    //get&&set
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
                }
