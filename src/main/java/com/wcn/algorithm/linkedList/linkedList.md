###将单向链表根据给定的值，调整为左边小于该值，中间等于该值，右边大于该值
例如 2-8-4-9-1-6-5-4-3 给定数值4 调整结果为 2-1-3-4-4-8-9-6-5
1. 直接转数组进行partition，再转回链表
2. 创建三个小中大链表，一次循环放入三种链表中，再将3个链表组合起来。

###能不能不给单向链表的头节点，只给想要删除的节点，就能做到在链表上删除该节点
不能！！！