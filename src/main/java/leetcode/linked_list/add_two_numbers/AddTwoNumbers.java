package leetcode.linked_list.add_two_numbers;

/**
 * <p>文件名称: </p>
 * <p>文件描述: </p>
 * <p>版权所有: 版权所有(C)2017</p>
 * <p>公 司: 深圳竹云科技有限公司</p>
 * <p>内容摘要: </p>
 * <p>其他说明: 无</p>
 * <p>完成日期: 2018/5/23</p>
 *
 * @author : OnlyLoveTianYa
 */
public class AddTwoNumbers {
    public static void main(String[] args) {

        AddTwoNumbers addTwoNumbers = new AddTwoNumbers();
        ListNode l1 = new ListNode(5);
//        l1.next = new ListNode(8);
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
