
/*
 *  Course: CS1011-051
 *  Fall 2020-2021
 *  File header contains class WaitingList
 *  Name: paganinik
 *  Created 3/10/2021
 */
package paganiniK;
import java.util.List;

/**
 * Course CS1011-051
 * Fall 2020-2021
 * WaitingList purpose: wait on a list
 *
 * @author paganinik
 * @version created on 3/10/2021 at 5:44 PM
 */
public class WaitingList {

    private List<ItemRequest> requests;

    /**
     * Creates empty list
     * @param requests
     */
    public WaitingList(List<ItemRequest> requests){
        this.requests = requests;
    }

    /**
     * checks if the request is fulfillable and returns the first element
     * @param isFulfillable
     * @return returns an itemrequest or null
     */
    public ItemRequest nextFulfillableRequest(boolean isFulfillable){
        if(isFulfillable){
            return requests.remove(0);
        } else {
            for(int i = 0; i < requests.size(); ++i){

            }
            return null;
        }


    }

    /**
     * adds a request to the end of the list
     * @param request
     */
    public void requestItem(ItemRequest request){
        requests.add(request);
    }

    /**
     * gets all the requests with a specific userID and adds them to another list
     * @param userId
     * @param userRequests
     */
    public void getAllRequestsFromUser(int userId, List<ItemRequest> userRequests){
        for(int i = 0; i < requests.size(); ++i){
            if(requests.get(i).getUserID() == userId){
                userRequests.add(requests.get(i));
            }
        }

    }

    /**
     * removes requests from the list using that specific request
     * @param request
     * @return returns whether or not the element could be removed
     */
    public boolean cancelRequest(ItemRequest request){
        return requests.remove(request);
    }

    /**
     * removes request using index
     * @param index
     * @return returns whether or not the element could be removed
     */
    public boolean removeRequest(int index){
        requests.remove(index);
        return true;
    }

    /**
     * Clears the entire list
     */
    public void clear(){
        requests.clear();
    }

    /**
     * Checks if the list is empty
     * @return if there is anything there
     */
    public boolean isEmpty(){
        return requests.isEmpty();
    }

    /**
     * gets a request at a specific index
     *
     * @param index
     * @return returns a itemrequest
     */
    public ItemRequest getRequest(int index){
        return requests.get(index);
    }

}

