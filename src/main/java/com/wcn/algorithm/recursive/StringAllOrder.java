package com.wcn.algorithm.recursive;

/**
 * 递归实现一个字符串的所有排序， 如：
 * ab 所有排列有： ab ba
 * abc 所有排列有： abc acb bac bca cab cba
 * 比如说abc，一个三个字符，第一步决定第一个字符，可以有三个分支（a\b\c）,然后再将可选分内容删除掉已选择的，再次做第二次选择。
 * 可以想成树，根节点有三个路径（a\b\c）,在下面的节点都有两个路径（要删除掉已选择的路径）
 */
public class StringAllOrder {

}
