package meesho.order.matching;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.TreeMap;

public class OrderMatchingProcessor {
	public static void main(String[] args) throws ParseException {
		final var orderMatchingProcessor = new OrderMatchingProcessor();
		final List<Order> orders = OrderMatchingUtils.getOrders();
		final List<MatchedOrder> matchedOrders = orderMatchingProcessor.matchOrders(orders);
		for (final MatchedOrder matchedOrder : matchedOrders) {
			System.out.println(matchedOrder);
		}
	}

	public List<MatchedOrder> matchOrders(List<Order> orders) {
		final List<MatchedOrder> matchedOrders = new ArrayList<>();

		final Map<String, Queue<Order>> sellOrderMap = new TreeMap<>();
		final Map<String, Queue<Order>> buyOrderMap = new TreeMap<>();

		Queue<Order> sellQueue = null;
		Queue<Order> buyQueue = null;

		for (final Order order : orders) {
			if (!buyOrderMap.containsKey(order.getName())) {
				buyOrderMap.put(order.getName(), new PriorityQueue<>());
			}
			if (!sellOrderMap.containsKey(order.getName())) {
				sellOrderMap.put(order.getName(), new PriorityQueue<>(Collections.reverseOrder()));
			}

			buyQueue = buyOrderMap.get(order.getName());
			sellQueue = sellOrderMap.get(order.getName());

			if (buyQueue == null || sellQueue == null) {
				return Collections.emptyList();
			}
			if (order.getOrderType() == OrderType.SELL) {
				sellQueue.add(order);
			} else if (order.getOrderType() == OrderType.BUY) {
				buyQueue.add(order);
			}
			while (!sellQueue.isEmpty() && !buyQueue.isEmpty()) {
				final var sellOrder = sellQueue.peek();
				final var buyOrder = buyQueue.peek();
				if (buyOrder.getPrice() >= sellOrder.getPrice()) {
					matchedOrders.add(match(buyOrder, sellOrder, buyQueue, sellQueue));
				} else {
					break;
				}
			}
		}

		return matchedOrders;
	}

	private MatchedOrder match(Order buyOrder, Order sellOrder, Queue<Order> buyQueue, Queue<Order> sellQueue) {
		if (buyOrder.getPrice() < sellOrder.getPrice()) {
			return null;
		}
		double quantitySold = 0;
		double buyOrderRemainingQuantity = 0;
		double sellOrderRemainingQuantity = 0;
		if (buyOrder.getQuantity() >= sellOrder.getQuantity()) {
			buyOrderRemainingQuantity = buyOrder.getQuantity() - sellOrder.getQuantity();
			quantitySold = sellOrder.getQuantity();
		} else {
			quantitySold = buyOrder.getQuantity();
			sellOrderRemainingQuantity = sellOrder.getQuantity() - quantitySold;
		}
		if (buyOrderRemainingQuantity == 0 && sellOrderRemainingQuantity == 0) {
			buyQueue.poll();
			sellQueue.poll();
		} else if (buyOrderRemainingQuantity == 0) {
			buyQueue.poll();
			sellOrder.setQuantity(sellOrderRemainingQuantity);
		} else {
			sellQueue.poll();
			buyOrder.setQuantity(buyOrderRemainingQuantity);
		}
		return new MatchedOrder(buyOrder.getId(), sellOrder.getId(), sellOrder.getPrice(), quantitySold);
	}
}

class OrderMatchingUtils {
	private static String STOCK_NAME = "Meesho"; // NOSONAR

	public static List<Order> getOrders() throws ParseException {
		final List<Order> orders = new ArrayList<>();

		final var date1 = getDate("09:45");
		final var date2 = getDate("09:46");
		final var date3 = getDate("09:47");
		final var date4 = getDate("09:48");
		final var date5 = getDate("09:49");
		final var date6 = getDate("09:50");

		final var order1 = new Order("1", date1, STOCK_NAME, OrderType.SELL, 240.12d, 100.0d);
		final var order2 = new Order("2", date2, STOCK_NAME, OrderType.SELL, 237.45, 90.0d);
		final var order3 = new Order("3", date3, STOCK_NAME, OrderType.BUY, 238.10d, 110.0d);
		final var order4 = new Order("4", date4, STOCK_NAME, OrderType.BUY, 237.8d, 10.0d);
		final var order5 = new Order("5", date5, STOCK_NAME, OrderType.BUY, 237.8d, 40.0d);
		final var order6 = new Order("6", date6, STOCK_NAME, OrderType.SELL, 236.0d, 50.0d);

		orders.add(order1);
		orders.add(order2);
		orders.add(order3);
		orders.add(order4);
		orders.add(order5);
		orders.add(order6);
		return orders;
	}

	private static Date getDate(String time) throws ParseException {
		final SimpleDateFormat sdf = new SimpleDateFormat("hh:mm");
		return sdf.parse(time);
	}
}

class Order implements Comparable<Order> {
	String id;
	Date time;
	String name;
	OrderType orderType;
	double price;
	double quantity;

	public Order() {
		super();
	}

	public Order(String id, Date time, String name, OrderType orderType, double price, double quantity) {
		super();
		this.id = id;
		this.time = time;
		this.name = name;
		this.orderType = orderType;
		this.price = price;
		this.quantity = quantity;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public OrderType getOrderType() {
		return orderType;
	}

	public void setOrderType(OrderType orderType) {
		this.orderType = orderType;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getQuantity() {
		return quantity;
	}

	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}

	@Override
	public int compareTo(Order o) {
		if (this.price == o.getPrice()) {
			return this.time.compareTo(o.getTime());
		}
		return (this.price < o.getPrice()) ? 1 : -1;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", time=" + time + ", name=" + name + ", orderType=" + orderType + ", price=" + price
				+ ", quantity=" + quantity + "]";
	}
}

class MatchedOrder {
	String buyOrderId;
	String sellOrderId;
	double sellPrice;
	double quantity;

	public MatchedOrder(String buyOrderId, String sellOrderId, double sellPrice, double quantity) {
		super();
		this.buyOrderId = buyOrderId;
		this.sellOrderId = sellOrderId;
		this.sellPrice = sellPrice;
		this.quantity = quantity;
	}

	public String getBuyOrderId() {
		return buyOrderId;
	}

	public void setBuyOrderId(String buyOrderId) {
		this.buyOrderId = buyOrderId;
	}

	public String getSellOrderId() {
		return sellOrderId;
	}

	public void setSellOrderId(String sellOrderId) {
		this.sellOrderId = sellOrderId;
	}

	public double getSellPrice() {
		return sellPrice;
	}

	public void setSellPrice(double sellPrice) {
		this.sellPrice = sellPrice;
	}

	public double getQuantity() {
		return quantity;
	}

	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "MatchedOrder [buyOrderId=" + buyOrderId + ", sellOrderId=" + sellOrderId + ", sellPrice=" + sellPrice
				+ ", quantity=" + quantity + "]";
	}
}

enum OrderType {
	BUY,
	SELL;
}