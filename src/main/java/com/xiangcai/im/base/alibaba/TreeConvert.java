package com.xiangcai.im.base.alibaba;

import lombok.Data;

/**
 * 3、 输入一棵二元查找树，将该二元查找树转换成一个排序的双向链表。
 * 要求不能创建任何新的结点，只调整引用（指针）的指向,只需要写出转换算法即可。
 * <p>
 * 思路：
 * 在二叉搜索树中，左子结点的值总是小于父结点的值，右子节点的值总是大于父结点的值。因此我们在转换成排序双向链表时，原先指向左子结点的指针调整为链表中指向前一个结点的指针，原先指向右子节点的指针调整为链表中指向后一个结点的指针。
 * 因为中序遍历是按照从小到大的顺序遍历二叉搜索树，所以我们用中序遍历树中的每一个节点得到的正好是要求的排好序的。遍历过程如下：
 * 每次遍历节点的左孩子、右孩子，把左孩子指向转换链表的尾节点，并把末尾指针的右孩子指向自己。右孩子指向节点的右孩子。如果没有右孩子就返回。这一过程可以用递归实现。
 *
 * @author :元放
 * @date :2020-04-28 23:17
 **/
public class TreeConvert {

    TreeNode head = null;
    TreeNode end = null;

    /**
     * 传递需要转换的跟节点
     *
     * @param pRootOfTree
     * @return
     */
    public TreeNode Convert(TreeNode pRootOfTree) {
        ConvertSub(pRootOfTree);
        return head;
    }
    public void ConvertSub(TreeNode pRootOfTree) {
        if(pRootOfTree == null)
            return ;
        Convert(pRootOfTree.left);
        if(end == null){
            head = pRootOfTree;
            end = pRootOfTree;
        }else{
            end.right = pRootOfTree;
            pRootOfTree.left = end;
            end = pRootOfTree;
        }
        Convert(pRootOfTree.right);
    }

     @Data
     class  TreeNode {
         TreeNode left;
         TreeNode right;
         int val = 0;

         public TreeNode(int val) {
             this.val = val;
         }
     }
}
