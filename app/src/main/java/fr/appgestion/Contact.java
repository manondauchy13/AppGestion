package fr.appgestion;


import com.google.firebase.firestore.Exclude;

public class Contact {

@Exclude

    private long id;
    private String  nom;
    private String tel;
    private String email;

    public Contact(){

    }

    public Contact(String nom, String tel, String email) {
        this.nom = nom;
        this.tel = tel;
        this.email = email;
    }

    public long getId() {
        return id;
    }

    @Exclude
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


}
