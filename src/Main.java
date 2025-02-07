import controllers.*;
import controllers.interfaces.IBookController;
import controllers.interfaces.ICartController;
import data.PostgresDB;
import data.interfaces.IDB;
import RoleManagement.Role;
import repositories.*;
import repositories.interfaces.*;
import start.*;


public class Main {
    public static void main(String[] args) {
        String url = "jdbc:postgresql://localhost:5432/bookstore";
        String user = "postgres";
        String password = "Shin127ay";
        IDB db = new PostgresDB(url, user, password);
        Role roll;
        boolean enabled;


        IUserRepository userRepo = new UserRepository(db);
        UserController userController = new UserController(userRepo);

        IBookRepository bookRepo = new BookRepository(db);
        IBookController bookController = new BookController(bookRepo);

        ICartRepository cartRepo = new CartRepository(db);
        ICartController cartController = new CartController(cartRepo);

        IPaymentRepository paymentRepo = new PaymentRepository(db);
        PaymentController paymentController = new PaymentController(paymentRepo);

        IReviewRepository reviewRepo = new ReviewRepository(db);
        ReviewController reviewController = new ReviewController(reviewRepo);
        ReviewApplication reviewApp = new ReviewApplication(reviewController);
2


        MyApplication app = new MyApplication(userController);
        BookApplication bookApp = new BookApplication(bookController);
        CartApplication cartApplication = new CartApplication(cartController);
        PaymentApplication paymentApp = new PaymentApplication(paymentController);

        Start starty = new Start(db);
        starty.start();
        roll = starty.role;
        enabled = starty.enter;

        app.start(roll);
        bookApp.start();
        cartApplication.start();
        paymentApp.start();

        db.close();
    }
}
