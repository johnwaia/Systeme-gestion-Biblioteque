package auth;

public class Client {
    private int id;
    private String nom;
    private String motDePasse;
    private boolean isAdmin;

    public Client(String nom, String motDePasse) {
        this.nom = nom;
        this.motDePasse = motDePasse;
        this.isAdmin = false; // Regular user by default
    }

    public Client(int id, String nom, String motDePasse, boolean isAdmin) {
        this.id = id;
        this.nom = nom;
        this.motDePasse = motDePasse;
        this.isAdmin = isAdmin;
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    // Assuming you might need to validate password in future, but keep plain text password secure
    public boolean isValidPassword(String password) {
        return this.motDePasse.equals(password);
    }

    // You may want to implement a secure way to update the password
    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    
}
