package leetcode.linked_list.add_two_numbers;

/**
 * 给定两个非空链表来表示两个非负整数。位数按照逆序方式存储，它们的每个节点只存储单个数字。将两数相加返回一个新的链表。
 * 你可以假设除了数字 0 之外，这两个数字都不会以零开头。
 * 示例：
 * <p>
 * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
 * 输出：7 -> 0 -> 8
 * 原因：342 + 465 = 807
 *
 * @author : OnlyLoveTianYa
 */
public class AddTwoNumbers {
    public static void main(String[] args) {
        AddTwoNumbers addTwoNumbers = new AddTwoNumbers();
        ListNode l1 = new ListNode(5);
        ListNode l2 = new ListNode(5);
        addTwoNumbers.addTwoNumbers(l1, l2);
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        boolean haveOverflow = false;
        boolean condition = true;
        boolean l1End = false;
        boolean l2End = false;
        ListNode L1backup = l1;
        ListNode L2backup = l2;
        while (condition) {
            if (!l1End && !l2End) {
                int sum;
                if (haveOverflow) {
                    sum = l1.val + l2.val + 1;
                    if (sum >= 10) {
                        sum = sum % 10;
                    } else {
                        haveOverflow = false;
                    }
                } else {
                    sum = l1.val + l2.val;
                    if (sum >= 10) {
                        sum = sum % 10;
                        haveOverflow = true;
                    }
                }
                l1.val = sum;
                l2.val = sum;

                if (l1.next == null && l2.next != null) {
                    l1End = true;
                }
                if (l1.next != null && l2.next == null) {
                    l2End = true;
                }
                if (l1.next == null && l2.next == null) {
                    l2End = true;
                    if (haveOverflow) {
                        l1.next = new ListNode(1);
                    }
                    condition = false;
                }
                l1 = l1.next;
                l2 = l2.next;
            } else if (!l1End && l2End) {
                int sum;
                if (haveOverflow) {
                    sum = l1.val + 1;
                    if (sum >= 10) {
                        sum = sum % 10;
                    } else {
                        haveOverflow = false;
                    }
                    l1.val = sum;
                }

                if (l1.next == null) {
                    if (haveOverflow) {
                        l1.next = new ListNode(1);
                    }
                    condition = false;
                }
                l1 = l1.next;
            } else {
                int sum;
                if (haveOverflow) {
                    sum = l2.val + 1;
                    if (sum >= 10) {
                        sum = sum % 10;
                    } else {
                        haveOverflow = false;
                    }
                    l2.val = sum;
                }

                if (l2.next == null) {
                    if (haveOverflow) {
                        l2.next = new ListNode(1);
                    }
                    condition = false;
                }
                l2 = l2.next;
            }
        }

        if (l1End) {
            return L2backup;
        } else {
            return L1backup;
        }
    }
}
