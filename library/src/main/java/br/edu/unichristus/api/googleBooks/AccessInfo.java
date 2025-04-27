package br.edu.unichristus.api.googleBooks;

public class AccessInfo {
    private Pdf pdf;

    public static class Pdf {
        private boolean isAvailable;
        private String acsTokenLink;

        public boolean isAvailable() {
            return isAvailable;
        }

        public void setIsAvailable(boolean isAvailable) {
            this.isAvailable = isAvailable;
        }

        public String getAcsTokenLink() {
            return acsTokenLink;
        }

        public void setAcsTokenLink(String acsTokenLink) {
            this.acsTokenLink = acsTokenLink;
        }
    }

    public Pdf getPdf() {
        return pdf;
    }

    public void setPdf(Pdf pdf) {
        this.pdf = pdf;
    }
}
