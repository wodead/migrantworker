package com.migrantworker;

public class RestoreTree {
    // [根，[左子树]，[右子树]]
    // [[左子树]，根，[右子树]]
    public static class TreeNode {
        public TreeNode left;
        public TreeNode right;
        public int value;

    }
    //查找根节点位置
    public static int findIndex(int[] tree, int left, int right, int value) {
        for (int i = left; i <= right; i++) {
            if (tree[i] == value)
                return i;
        }
        throw new RuntimeException("Invalid tree");
    }

    public static TreeNode restore(int[] xianXu, int xianXuLeftIndex, int xianXuRightIndex, int[] zhongXu, int zhongXuLeftIndex, int zhongXuRightIndex) {
        int root = xianXu[xianXuLeftIndex];
        TreeNode treeNode = new TreeNode();
        treeNode.value = root;
        if (zhongXuLeftIndex == zhongXuRightIndex)
            return treeNode;
        if (zhongXuLeftIndex > zhongXuRightIndex)
            return null;

        int index = findIndex(zhongXu, zhongXuLeftIndex, zhongXuRightIndex, root);
        int leftSize = index - zhongXuLeftIndex;
        //左子树
        treeNode.left = restore(xianXu, xianXuLeftIndex + 1, xianXuLeftIndex + leftSize,  zhongXu, zhongXuLeftIndex, index - 1);
        //右子树
        treeNode.right = restore(xianXu, xianXuLeftIndex + leftSize + 1, xianXuRightIndex,  zhongXu, index + 1, zhongXuRightIndex);
        return treeNode;
    }

    public static void main(String[] args) {
        int[] xianXu = {1,2,3};
        int[] zhongXu = {2,1,3};
        TreeNode treeNode = restore(xianXu, 0, xianXu.length - 1, zhongXu, 0, zhongXu.length - 1);
        System.out.println(treeNode.value);
    }
}
