/**
 * Course CS1011-051
 * Fall 2020-2021
 * ItemRequest purpose: request items
 *
 * @author paganinik
 * @version created on 3/10/2021 at 2:00 PM
 */
package paganiniK;

/**
 * Request Items
 */
public class ItemRequest {
    private final int userID;
    private final int itemID;

    /**
     * Makes an Item request
     * @param userID
     * @param itemID
     */
    public ItemRequest(int userID, int itemID){
        this.itemID = itemID;
        this.userID = userID;

    }

    public int getItemID() {
        return itemID;
    }

    public int getUserID() {
        return userID;
    }
}
