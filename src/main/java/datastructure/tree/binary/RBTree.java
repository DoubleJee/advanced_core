package datastructure.tree.binary;

import java.util.Comparator;

// 红黑树 基础于二叉搜索树
public class RBTree<E extends Comparable> extends BalancedBinarySearchTree<E>{

    private static final boolean RED = false;

    private static final boolean BLACK = true;


    public RBTree() {
        this(null);
    }

    public RBTree(Comparator comparator) {
        super(comparator);
    }


    @Override
    protected Node<E> createNode(E element, Node<E> parent) {
        RBNode<E> rbNode = new RBNode<>(element, parent);
        if (root == null){
            black(rbNode);
        }
        return rbNode;
    }

    @Override
    protected void afterAdd(Node<E> node) {

        Node<E> parent = node.parent;

        // 添加的是根节点 或者 上溢到根节点
        if (parent == null){
            black(node);
            return;
        }

        // 如果添加节点的父节点是BLACK，则不处理
        if (isBlack(parent)) return;

        // 父节点为RED，需要维持红黑树的性质
        Node<E> grand = parent.parent;
        Node<E> uncle = parent.sibling();

        // uncle节点为红色 操作公式：将parent、uncle染成BLACK，grand向上合并当成新添加的元素递归修复
        if (isRed(uncle)){

            black(parent);
            red(grand);
            black(uncle);

            // 递归去修复
            afterAdd(grand);
            return;
        }


        // uncle节点为黑色 根据情况对应旋转一次即可
        if (node.isLeftChildOfParent()){
            // LL
            if (parent.isLeftChildOfParent()){
                rotate(grand,node.left,node,node.right,parent,parent.right,grand,grand.right);

//                // parent染成BLACK，grand染成RED
//                black(parent);
//                red(grand);
//                rotateRight(grand);
            } else {
                // RL
                rotate(grand,grand.left,grand,node.left,node,node.right,parent,parent.right);

//                // f染成BLACK，grand染成RED
//                black(node);
//                red(grand);
//                rotateRight(parent);
//                rotateLeft(grand);
            }
        }else if (node.isRightChildOfParent()){
            // RR
            if (parent.isRightChildOfParent()) {
                rotate(grand,grand.left,grand,parent.left,parent,node.left,node,node.right);

//                // parent染成BLACK，grand染成RED
//                black(parent);
//                red(grand);
//                rotateLeft(grand);
            }else {
                // LR
                rotate(grand,parent.left,parent,node.left,node,node.right,grand,grand.right);

//                // f染成BLACK，grand染成RED
//                black(node);
//                red(grand);
//                rotateLeft(parent);
//                rotateRight(grand);
            }
        }


    }


    @Override
    protected void afterRemove(Node<E> node) {
        // 删除节点为RED，不需要处理，没有破坏性质
        if (isRed(node)) return;

        // 删除节点为BLACK，可能需要处理，可能会破坏性质

        // 度为1，可能需要处理，可能会破坏性质，如果用以替代的子节点是RED，将替代的子节点染为黑色
        // if (!node.isRightChildOfParent() && !node.isLeftChildOfParent())  是为了兼顾到兄弟没有一个RED节点进行上溢，并且parent是black节点当成新删除节点递归调用修复的情况，不能将parent认为下面还有节点，应认为parent是叶子节点
        if (!node.isRightChildOfParent() && !node.isLeftChildOfParent()){
            Node<E> replaceNode = node.left == null ? node.right : node.left;
            if (isRed(replaceNode)){
                black(replaceNode);
                return;
            }
        }


        // 删除BLACK叶子节点，会导致下溢
        Node<E> parent = node.parent;

        // 为根节点 不需要处理，没有破坏性质
        if (parent == null) return;

        // node.isLeftChildOfParent() 是为了兼顾到兄弟没有一个RED节点进行上溢，并且parent是black节点当成新删除节点递归调用修复的情况（parent的父节点还在指向它，并没有真正删除，断线），帮助得到正确的兄弟节点
        Node<E> sibling = node.isLeftChildOfParent() || node.parent.left == null ? node.parent.right : node.parent.left;
        // 兄弟节点是BLACK
        if (isBlack(sibling)){
            // 兄弟节点，有一个RED子节点（借兄弟的子节点），根据parent - sibling - sibling的RED子节点进行旋转
            // 旋转中心节点继承parent的颜色，旋转后的左右节点染成黑色
            if (isRed(sibling.left)){
                if (sibling.isLeftChildOfParent()){
                    // LL
                    rotateRight(parent);

                    black(sibling.left);
                    color(sibling,((RBNode<E>)parent).color);
                    black(parent);


                }else {
                    // RL
                    rotateRight(sibling);
                    rotateLeft(parent);

                    black(sibling);
                    color(sibling.left,((RBNode<E>)parent).color);
                    black(parent);
                }
            }else if (isRed(sibling.right)){
                if (sibling.isLeftChildOfParent()){
                    // LR
                    rotateLeft(sibling);
                    rotateRight(parent);

                    black(sibling);
                    color(sibling.right,((RBNode<E>)parent).color);
                    black(parent);


                }else {
                    // RR
                    rotateLeft(parent);

                    black(sibling.right);
                    color(sibling,((RBNode<E>)parent).color);
                    black(parent);
                }
            }else {
                // 兄弟节点，没有一个RED子节点（下溢，父节点向下合并），将sibling节点染成红色，parent节点染成黑色
                red(sibling);
                // 如果原本parent节点是BLACK，会导致再次上溢，将父节点当成删除的节点，继续修复
                if (isBlack(parent)){
                    // 会有2处特殊情况处理，在方法有注释提到
                    afterRemove(parent);
                    return;
                }
                black(parent);
            }

        }else {
            // 兄弟节点是RED
            // 对parent - sibling - 删除节点相对的sibling的远邻节点（就是真正要变为兄弟节点的它的另外一边的节点）进行相应情况的旋转，将sibling节点染成黑色，parent节点染成红色
            // 经过旋转会得到正确的兄弟节点，sibling会成为整棵子树的新BLACK根节点，子节点指向之前的父节点（变为了RED），父节点带着正确的兄弟节点，指向自己
            if (sibling.isLeftChildOfParent()){
                // LL
                rotateRight(parent);

            }else {
                // RR
                rotateLeft(parent);
            }

            red(parent);
            black(sibling);
            // 得到了正确的BLACK 兄弟节点，继续修复
            afterRemove(node);
        }

    }

    @Override
    protected void rotate(Node<E> r, Node<E> a, Node<E> b, Node<E> c, Node<E> d, Node<E> e, Node<E> f, Node<E> g) {
        super.rotate(r, a, b, c, d, e, f, g);
        //染色
        black(d);

        black(a);
        red(b);
        black(c);

        black(e);
        red(f);
        black(g);
    }

    // 染色
    private Node<E> color(Node<E> node,boolean color){
        if (node == null) return node;
        ((RBNode<E>)node).color = color;
        return node;
    }

    // 染红色
    private Node<E> red(Node<E> node){
        return color(node,RED);
    }

    // 染黑色
    private Node<E> black(Node<E> node){
        return color(node,BLACK);
    }

    // 节点颜色
    private boolean colorOf(Node<E> node){
        // 如果是null的话 代表空节点
        if (node == null) return BLACK;
        return ((RBNode<E>)node).color;
    }

    // 是否红色节点
    private boolean isRed(Node<E> node){
        return colorOf(node) == RED;
    }

    // 是否黑色节点
    private boolean isBlack(Node<E> node){
        return colorOf(node) == BLACK;
    }


    class RBNode<E> extends Node<E>{
        private boolean color = RED;
        public RBNode(E element, Node<E> parent) {
            super(element, parent);
        }

        @Override
        public String toString() {
            return (color == RED ? "R" : "B") + "_" + super.toString();
        }
    }
}
