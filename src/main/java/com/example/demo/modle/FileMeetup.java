    package com.example.demo.modle;

    import jakarta.persistence.*;

    //enum MeetingFileType{
    //    IMG,
    //    VIDIO,
    //    //מצגת
    //    PRESENT
    //}
    @Entity
    public class FileMeetup {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
    //    @Enumerated(EnumType.STRING)
    //    private MeetingFileType type;
        //במקום ENUM
        private String typeFile;
        private String url_file;
        //השם של קובץ הPDF
        private String name;

        //תלות
        @ManyToOne
        @JoinColumn(name = "gallery_category_id", nullable = false)
        private GalleryCategory gallery_category;

        //get&&set
        public String getTypeFile() {
            return typeFile;
        }

        public void setTypeFile(String typeFile) {
            this.typeFile = typeFile;
        }

        public GalleryCategory getGallery_category() {
            return gallery_category;
        }

        public void setGallery_category(GalleryCategory gallery_category) {
            this.gallery_category = gallery_category;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

//        public MeetingFileType getType() {
//            return type;
//        }
//
//        public void setType(MeetingFileType type) {
//            this.type = type;
//        }

        public String getUrl_file() {
            return url_file;
        }

        public void setUrl_file(String url_file) {
            this.url_file = url_file;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public Long getId() {
            return id;
        }


    }
