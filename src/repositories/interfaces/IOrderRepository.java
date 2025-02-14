package repositories.interfaces;

import models.Order;

public interface IOrderRepository {
    Order getFullOrderDescription(int orderId);
}
