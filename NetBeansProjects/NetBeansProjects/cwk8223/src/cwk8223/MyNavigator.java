/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cwk8223;

import java.util.ArrayList;

/**
 * Implementation of Navigator interface. You need to complete this file.
 *
 * @author David Sutton
 */
public class MyNavigator implements Navigator {

    @Override
    public ArrayList<String> breadthFirst(Network network, String start) {
        MyStringQueue  queue = new MyStringQueue();
        queue.add(start);
        ArrayList<String> output = new ArrayList<>();
        while(queue.getSize()>0){
            String current = queue.remove();
            output.add(current);
            for(String s:network.getConnections(current)){
                if(!output.contains(s)){
                    queue.add(s);
                }
            }
        }
        return output;
    }

    @Override
    public ArrayList<String> depthFirst(Network network, String start) {
        MyStringStack stack = new MyStringStack();
        stack.push(start);
        ArrayList<String> output = new ArrayList<>();
        while(stack.getSize()>0){
            String current = stack.pop();
            if(!output.contains(current)){
                output.add(current);
            }
            for(String s:network.getConnections(current)){
                if(!output.contains(s)){
                    stack.push(s);
                }
            }
            
        }
        return output;
    }
}
