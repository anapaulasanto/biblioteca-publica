package br.edu.unichristus.biblioteca.client.googlebooks;

public class AcessInfo {
    private Pdf epub;
    private Pdf pdf;

    public static class Pdf {
        private boolean isAvailable;
        private String acsTokenLink;

        public boolean isAvaliable() {
            return isAvailable;
        }

        public void setAvaliable(boolean avaliable) {
            isAvailable = avaliable;
        }

        public String getAcsTokenLink() {
            return acsTokenLink;
        }

        public void setAcsTokenLink(String acsTokenLink) {
            this.acsTokenLink = acsTokenLink;
        }
    }

    public Pdf getEpub() {
        return epub;
    }

    public void setEpub(Pdf epub) {
        this.epub = epub;
    }

    public Pdf getPdf() {
        return pdf;
    }

    public void setPdf(Pdf pdf) {
        this.pdf = pdf;
    }
}
