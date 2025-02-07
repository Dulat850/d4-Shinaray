package RoleManagement;

import models.Admin;

public class Admin_Can extends Admin {
    private boolean canGetAllBooks;
    private boolean canGetBooksById;
    private boolean canGetBooksByName;
    private boolean canCreateBook;
    private boolean canAddBookToCart;
    private boolean canDeleteBookFromCart;
    private boolean canUpdateBookFromCart;
    private boolean canGetTotalCost;
    private boolean canClearCart;
    private boolean canViewCart;
    private boolean canCreateUser;
    private boolean canUpdateUser;
    private boolean canGetAllUsers;
    private boolean canGetUsersById;
    private boolean canGetUsersByName;

    public Admin_Can(int id, String name, String email, String password, boolean canManageBooks, boolean canManageUsers) {
        super(id, name, email, password);
        this.canGetAllBooks = canManageBooks;
        this.canCreateBook = canManageBooks;
        this.canGetAllUsers = canManageUsers;
        this.canCreateUser = canManageUsers;
    }

    public boolean canGetAllBooks() { return canGetAllBooks; }
    public boolean canCreateBook() { return canCreateBook; }
    public boolean canGetAllUsers() { return canGetAllUsers; }
    public boolean canCreateUser() { return canCreateUser; }


    public void setCanGetAllBooks(boolean canGetAllBooks) { this.canGetAllBooks = canGetAllBooks; }
    public void setCanCreateBook(boolean canCreateBook) { this.canCreateBook = canCreateBook; }
    public void setCanGetAllUsers(boolean canGetAllUsers) { this.canGetAllUsers = canGetAllUsers; }
    public void setCanCreateUser(boolean canCreateUser) { this.canCreateUser = canCreateUser; }
}
