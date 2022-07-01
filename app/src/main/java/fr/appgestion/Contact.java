package fr.appgestion;


public class Contact {


    long id;
    String nom,com,tel;

  Contact(){

    }

    public Contact(String nom, String tel, String com) {
        this.nom = nom;
        this.tel = tel;
        this.com = com;
    }

    public long getId() {
        return id;
    }


    public void setId(long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getCom() {
        return com;
    }

    public void setCom(String com) {
        this.com = com;
    }


}
