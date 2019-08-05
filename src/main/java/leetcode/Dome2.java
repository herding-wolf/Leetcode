package leetcode;

import lombok.Data;

/**
 * @author luochong
 * @date 2019/8/2 11:21
 * 给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
 *
 * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
 * 可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 */
public class Dome2 {

    public static void main(String[] args) {
        int[] arr1 = new int[]{2, 4, 3};
        int[] arr2 = new int[]{5, 6, 4};
//        int[] arr1 = new int[]{1};
//        int[] arr2 = new int[]{9, 9};

        ListNode l1 = new ListNode(arr1);
        ListNode l2 = new ListNode(arr2);

        ListNode listNode1 = new ListNode(new int[]{1,1});
        ListNode listNode2 = new ListNode(2);
        ListNode listNode3 = new ListNode(3);
//        listNode1.setNext(listNode2);
//        listNode2.setNext(listNode3);

        listNode1.addNode(listNode2);
        listNode1.addNode(listNode3);
        System.out.println(listNode1.getLength());
        System.out.println(listNode1);

        ListNode listNode = new Solution().addTwoNumbers(l1, l2);
        System.out.println(listNode.toString());
    }

    static class Solution {
        public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
            ListNode result = new ListNode(0), resultItem = result;
            ListNode p1 = l1, p2 = l2;
            int carry = 0;
            while (p1 != null || p2 != null || carry == 1) {
                int sum = (p1 == null ? 0 : p1.val) + (p2 == null ? 0 : p2.val) + carry;
                resultItem.next = new ListNode(sum % 10);
                resultItem = resultItem.next;
                carry = sum / 10;
                p1 = p1 == null ? null : p1.next;
                p2 = p2 == null ? null : p2.next;
            }
            return result.next;
        }
    }

    @Data
    static class ListNode {

        private int val;

        private ListNode next;

        private int length = 1;

        private ListNode tail;

        ListNode(int x) { val = x; }

        ListNode(int[] arr) {
            this(arr[0]);
            ListNode nextItem = this;
            for (int i=1; i<arr.length; i++) {
                nextItem.next = new ListNode(arr[i]);
                nextItem = nextItem.next;
            }
            tail = nextItem;
            length = arr.length;
        }

        public void addNode(ListNode node) {
//            ListNode nextItem = this;
//            while (nextItem.next != null) {
//                nextItem = nextItem.next;
//            }
            tail.next = node;
            tail = tail.next;
            length++;
        }

        @Override
        public String toString() {
            StringBuffer sb = new StringBuffer();
            ListNode item = this;
            while (item != null) {
                sb.append(item.val + " -> ");
                item = item.next;
            }
            return sb.toString();
        }
    }
}
