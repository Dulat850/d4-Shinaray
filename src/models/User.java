
package models;

import RoleManagement.Role;

public class User {
    private int id;
    private String name;
    private String email;
    private String password;
    private Role role = Role.USER;

    public User(){

    }

    public User(String name, String email, String password) {
        setName(name);
        setEmail(email);
        setPassword(password);
    }

    public User(int id, String name, String email, String password) {
        this(name, email, password);
        setId(id);
    }

    public User(int id, String name, String email, String password, Role role) {
        this(name, email, password);
        setId(id);
        setRole(role);
    }

    public User(int userId, String userName, String email) {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
