package live.xiaoxu.util.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * <p>二叉树工具类，需继承 {@link BinaryTreeNode BinaryTreeNode}</p>
 *
 * @author 小徐
 * @since 2023/5/24 15:40
 */
public class XBinaryTreeUtils {

    /**
     * 禁止实例化
     */
    private XBinaryTreeUtils() {
        throw new IllegalAccessError("XBinaryTreeUtils.class");
    }

    /**
     * <p>获取以某节点为子树的高度</p>
     * <p>求二叉树的高度：</p>
     * <p>首先要一种获取以某个节点为子树的高度的方法，使用递归调用。</p>
     * <p>如果一个节点为空，那么这个节点肯定是一颗空树，高度为 0；</p>
     * <p>如果不为空，那么我们要遍历地比较它的左子树高度和右子树高度，高的一个为这个子树的最大高度，然后加上自己本身的高度就是了</p>
     * <p>获取二叉树的高度，只需要调用第一种方法，即传入根节点</p>
     */
    public static <T> int height(BinaryTreeNode<T> node) {

        if (node == null) {
            // 递归结束，空子树高度为 0
            return 0;
        } else {
            // 递归获取左子树高度
            int l = height(node.getLeft());
            // 递归获取右子树高度
            int r = height(node.getRight());
            // 高度应该算更高的一边，（+1 是因为要算上自身这一层）
            return l > r ? (l + 1) : (r + 1);
        }
    }

    public static <T> BinaryTreeNode<T> toTree(List<T> list) {

        int index = 0;
        LinkedList<BinaryTreeNode<T>> linkedList = new LinkedList<>();
        linkedList.add(new BinaryTreeNode<>());
        while (index < list.size()) {

            BinaryTreeNode<T> node = linkedList.poll();
            if (node == null) {
                break;
            }
            node.setData(list.get(index));
            if ((index + 1) * 2 - 1 < list.size()) {
                node.setRight(new BinaryTreeNode<>());
                linkedList.add(node.getLeft());
            }
            if ((index + 1) * 2 < list.size()) {
                node.setRight(new BinaryTreeNode<>());
                linkedList.add(node.getRight());
            }
            index++;
        }
        return new BinaryTreeNode<T>();
    }

    public static <T> List<T> toList(BinaryTreeNode<T> list) {

        return new ArrayList<>();
    }
}