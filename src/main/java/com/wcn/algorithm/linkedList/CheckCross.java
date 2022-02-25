package com.wcn.algorithm.linkedList;

/**
 * 返回两个链表的相交点，要求时间复杂度是O(N)，额外空间复杂度是O(1)
 * 不考虑额外空间复杂度可以使用HashMap实现，将第一个链表节点全放进去（有环不重复访就好），再依次遍历第二个链表，去map中校验是否包含即可。
 *
 * 额外空间复杂度为O(1)的情况：
 * 如果两个链表无环，如果发生相交，则最后一个节点肯定一样，因为如果香蕉了肯定是一个Y形状。
 * 如果两个链表一个有环，一个无环，则肯定不相交
 * 如果两个链表都有环，而且两个链表相交，分两种情况：
 *      1：两个入环点一样，表示环外就已经相交，形状像一个Y下面挂一个环，相交点还是在Y上。可以将两个链表从头开始到入环点，根据无环套路找到相交点。
 *      2：两个入环点不一样，表示环外无相交。则用任意一个链表的入环点继续绕环一周，如果期间能碰到另一个链表的入环点，则相交了，则返回任意一个入环点都表示相交点。
 */
public class CheckCross {
    public static void main(String[] args) {
        Node node1 = new Node("1");
        Node node2 = new Node("2");
        Node node3 = new Node("3");
        Node node4 = new Node("4");
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;

        Node nodeA = new Node("A");
        Node nodeB = new Node("B");
        nodeA.next = nodeB;
        nodeB.next = node3;
        System.out.println("无环相交于："+ checkAndGetNoCircleCross(node1, nodeA));


        Node node5 = new Node("5");
        Node node6 = new Node("6");
        Node node7 = new Node("7");
        Node node8 = new Node("8");
        Node node9 = new Node("9");
        node4.next = node5;
        node5.next = node6;
        node6.next = node7;
        node7.next = node8;
        node8.next = node9;
        node9.next = node7;//从7入环
        System.out.println("入环点:"+getInCircleNode(node1));
        System.out.println("环外相交点3:"+checkAndGet(node1, nodeA));
        nodeB.next = node8;
        System.out.println("环内相交点:"+checkAndGet(node1, nodeA));
    }

    static class Node{
        public String value;
        public Node next;

        public Node(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "value='" + value + '\'' +
                    '}';
        }
    }

    public static Node checkAndGet(Node head1, Node head2){
        Node inCircleNode1 = getInCircleNode(head1);//链表1的入环点
        Node inCircleNode2 = getInCircleNode(head2);//链表2的入环点
        if(inCircleNode1==null && inCircleNode2==null){
            //两个都无环
            return checkAndGetNoCircleCross(head1, head2);
        }else if(inCircleNode1!=null && inCircleNode2!=null){
            //两个都有环
            return checkAndGetCircleCross(head1, inCircleNode1, head2, inCircleNode2);
        }else{
            //一个有环一个无环，则肯定不相交
            return null;
        }
    }

    /**
     * 判断链表是否有环，有环的话返回入环点
     * @param head
     * @return
     */
    public static Node getInCircleNode(Node head){
        if(head==null || head.next==null){
            return null;
        }
        Node slow = head;
        Node fast = head;
        boolean crossFlag = false;
        while(fast.next!=null && fast.next.next!=null){
            slow = slow.next;
            fast = fast.next.next;

            if(slow.equals(fast)){
                //相交了
                crossFlag = true;
                break;
            }
        }

        if(!crossFlag){
            return null;
        }

        //slow从head.next开始，fast从head.next.next开始，则第一次fast嘴上show后，fast再次从head开始每次一步，slow继续，
        //则再次相遇的点必是入环点
        fast = head;
        while(fast!=slow){
            fast = fast.next;
            slow = slow.next;
        }
        return fast;
    }


    public static Node checkAndGetCircleCross(Node head1, Node inCircleNode1, Node head2, Node inCircleNode2){
        if(inCircleNode1.equals(inCircleNode2)){
            //表示环外相交了，将环截掉，再次使用无环相交点方法
            inCircleNode1.next = null;
            return checkAndGetNoCircleCross(head1, head2);
        }else{
            boolean crossFlag = false;
            Node temp = inCircleNode1.next;
            while(temp.equals(inCircleNode1)){
                //inCircleNode1绕环一周再到自己，路途中判断是否碰到了inCircleNode2
                if(temp.equals(inCircleNode2)){
                    crossFlag = true;
                }
                temp = temp.next;
            }
            if(!crossFlag){
                return null;
            }
            return inCircleNode1;
        }
        
    }

    /**
     * 校验两个无环链表是否相交,并返回相交点
     * @param head1
     * @param head2
     * @return 相交点
     */
    public static Node checkAndGetNoCircleCross(Node head1, Node head2){
        if(head1==null || head2==null){
            return null;
        }
        int length1 = 0;
        int length2 = 0;

        Node temp1 = head1;
        Node tail1 = null;//链表1的尾节点
        while(temp1!=null){
            length1++;
            if(temp1.next==null){
                tail1 = temp1;
                break;
            }
            temp1 = temp1.next;
        }

        Node temp2 = head2;
        Node tail2 = null;//链表2的尾部节点
        while(temp2!=null){
            length2++;
            if(temp2.next==null){
                tail2 = temp2;
            }
            temp2 = temp2.next;
        }

        System.out.println("debug---------------------------------length1:"+length1+" length2:"+length2);
        //尾部节点不相等，表示没有相交
        if(!tail1.equals(tail2)){
            return null;
        }

        //如果两个链表相交，则从较长的链表先走几步，达到一样长的时候，两个链表一起往前走，一直比对当前节点是否一样，第一个一样的节点即为相交点
        temp1 = head1;
        temp2 = head2;
        if(length1>length2){
            while(length1!=length2){
                temp1 = temp1.next;
                length1--;
            }
        }else if(length1<length2){
            while(length1!=length2){
                temp2 = temp2.next;
                length2--;
            }
        }

        while(temp1!=null){
            if(temp1.equals(temp2)){
                return temp1;
            }
            temp1 = temp1.next;
            temp2 = temp2.next;
        }

        return null;
    }
}
