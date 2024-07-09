


# README for Package GUI

## Overview
This Java Swing application provides a graphical user interface for managing documents within a database. Users can add books, magazine reviews, and videos, search for documents based on title and author, and manage their sessions through user authentication. The application utilizes JDBC for database connectivity and includes various GUIs built using Java Swing.

### Features
- **Document Management:**
  - Add different types of documents (Books, Magazine Reviews, Videos).
  - Search for documents based on title and author.
  - Display documents with optional PDF file downloads.
  
- **User Authentication and Management:**
  - Login and registration for users.
  - Administrators can delete documents.
  - Session management with logout functionality.

### Dependencies
- **Java Development Kit (JDK)**: Required to compile and run Java programs.
- **Swing GUI Toolkit**: Part of the JDK, used for building graphical user interfaces in Java.
- **JDBC Driver**: Required for database connectivity (`DatabaseConnection.java`).
- **Client Class**: Provides client information, including client ID (`client.getId()`).

### GUI Components
1. **AddDocumentGUI**
   - Allows users to input details for adding Books, Magazine Reviews, or Videos.
   - Validates user input and handles optional PDF file attachment.
   - Integrates with JDBC to insert data into the database.

2. **ConnexionInscriptionGUI**
   - Provides login and registration functionality.
   - Allows users to authenticate with their username and password.
   - Supports registration as an administrator.

3. **DocumentTypeSelectionGUI**
   - Allows users to select the type of document they want to add (Book, Magazine Review, Video).
   - Opens respective GUIs (`LivreGUI`, `RevueGUI`, `VideoGUI`) for adding documents.

4. **MenuPrincipalGUI**
   - Main menu interface for users.
   - Options to add documents, search documents, delete documents (for administrators), and logout.
   - Provides a welcome message and dynamically displays buttons based on user roles.

5. **SearchDocumentGUI**
   - Allows users to search for documents (Books, Magazine Reviews, Videos) based on title and author.
   - Displays search results with optional PDF file downloads.
   - Includes navigation back to the main menu.

6. **LivreGUI**
   - GUI for adding Books (`Livre`) to the database.
   - Input fields for title, author, genre, and year.
   - Validates input and utilizes `LivreBuilder` for adding books.

7. **RevueGUI**
   - GUI for adding Magazine Reviews (`Revue`) to the database.
   - Input fields for title, author, genre, and year.
   - Validates input and interacts with `RevueBuilder` for adding reviews.

8. **VideoGUI**
   - GUI for adding Videos to the database.
   - Input fields for title, author, genre, and year.
   - Validates input and utilizes `VideoBuilder` for adding videos.

### Usage
1. **Setup**
   - Ensure JDK is installed and configured.
   - Update database connection details in `DatabaseConnection.java`.

2. **Compilation and Execution**
   - Compile the Java files (`javac *.java`).
   - Run the application (`java MainClass`).

3. **Login**
   - Use `ConnexionInscriptionGUI` to login with existing credentials or register a new account.

4. **Main Menu**
   - Navigate through options (`Ajouter un document`, `Rechercher un document`, `Supprimer un document`, `Déconnexion`) in `MenuPrincipalGUI`.

5. **Document Management**
   - Add new documents through `AddDocumentGUI`.
   - Search for documents using `SearchDocumentGUI`.
   - Administrators can delete documents using the respective options.

### Development Notes
- Use layout managers (`BorderLayout`, `GridLayout`, `GridBagLayout`) for better UI responsiveness.
- Implement robust error handling and input validation across all GUIs.
- Secure database interactions using prepared statements to prevent SQL injection.
- Enhance user feedback with status messages and notifications.

### Authors
- Developed by John WAIA

