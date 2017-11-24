/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.struts2;

import com.opensymphony.xwork2.ActionContext;
import java.util.Map;
import java.util.StringTokenizer;
import sample.cart.CartObj;
import sample.cart.QuantityObj;
import sample.tbl_mobile.Tbl_MobileDAO;
import sample.tbl_orderdetail.Tbl_OrderDetaillDAO;

/**
 *
 * @author Nguen Cung
 */
public class PayCartAction {

    private String[] chkbox;
    private final String SUCCESS = "success";
    private final String FAIL = "fail";

    public String[] getChkbox() {
        return chkbox;
    }

    public void setChkbox(String[] chkbox) {
        this.chkbox = chkbox;
    }

    public PayCartAction() {
    }

    public String execute() throws Exception {
        String url = FAIL;
        Map session = ActionContext.getContext().getSession();
        CartObj cart = (CartObj) session.get("CART");
        QuantityObj quantity = new QuantityObj();
        Tbl_MobileDAO dao = new Tbl_MobileDAO();
        int quantityInStock = 0;
        int count = 0;
        if (cart != null) {
            Map<String, QuantityObj> items = cart.getItems();
            for (Map.Entry<String, QuantityObj> item : items.entrySet()) {
                String idMobile = item.getKey();
                StringTokenizer st = new StringTokenizer(idMobile, ",");
                idMobile = st.nextToken();
                quantity = item.getValue();
                quantityInStock = dao.getQuantityProduct(idMobile);
                if (quantityInStock != -1) {
                    quantityInStock = quantityInStock - quantity.getQuantityUser();
                } else {
                    quantityInStock = 0;
                    quantity.setQuantityUser(0);
                    --count;
                }
                quantity.setQuantityinStock(quantityInStock);
                if (quantityInStock >= 0) {
                    ++count;
                }
                items.put(item.getKey(), quantity);
            }
            if (count == items.size()) {
                Tbl_OrderDetaillDAO order = new Tbl_OrderDetaillDAO();
                int orderId = order.getOrderId() + 1;
                if (orderId == 0) {
                    orderId = 1;
                }
                for (Map.Entry<String, QuantityObj> item : items.entrySet()) {
                    String idMobile = item.getKey();
                    StringTokenizer st = new StringTokenizer(idMobile, ",");
                    idMobile = st.nextToken();
                    quantity = item.getValue();
                    order.insertNewOrder(orderId, cart.getUserId(), idMobile, quantity.getQuantityUser());
                    dao.updateRecord(idMobile, quantity.getQuantityinStock());
                }
                session.remove("CART");
                url = SUCCESS;
            } else {
                cart.setItems(items);
                session.put("CART", cart);
            }
        }
        return url;
    }

    public String RemoveItems() throws Exception {
        Map session = ActionContext.getContext().getSession();
        CartObj cart = (CartObj) session.get("CART");
        if (cart != null) {
            String[] selectedItems = chkbox;
            if (selectedItems != null) {
                for (String item : selectedItems) {
                    System.out.println(item);
                    cart.removeItemFromCart(item);
                }
                session.put("CART", cart);
            }
        }
        return FAIL;
    }

}
