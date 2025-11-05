public class BinarySearchTreeDemo {

    static class TreeNode {
        int value;
        TreeNode left;
        TreeNode right;
        TreeNode parent;

        TreeNode(int value) {
            this.value = value;
        }
    }


    static class BinarySearchTree {

        TreeNode root;

        public TreeNode findValue(TreeNode current, int target) {
            if (current == null) {
                return null;
            }

            if (current.value == target) {
                return current;
            }

            if (target < current.value) {
                return findValue(current.left, target);
            } else {
                return findValue(current.right, target);
            }
        }


        public TreeNode find(int target) {
            return findValue(root, target);
        }


        public void insertNode(TreeNode current, int newValue) {
            if (newValue == current.value) {
                System.out.println("Valor duplicado");
                return;
            }

            if (newValue < current.value) {
                if(current.left != null) {
                    insertNode(current.left, newValue);
                } else {
                    current.left = new TreeNode(newValue);
                    current.left.parent = current;
                }
            }  else {
                if(current.right != null) {
                    insertNode(current.right, newValue);
                } else {
                    current.right = new TreeNode(newValue);
                    current.right.parent = current;
                }
            }
        }


        public void insert(int newValue) {
            if (root == null) {
                root = new TreeNode(newValue);
            } else {
                insertNode(root, newValue);
            }
        }


        private void inorderRec(TreeNode node) {
            if (node != null) {
                inorderRec(node.left);
                System.out.print(node.value + " ");
                inorderRec(node.right);
            }
        }

        public void inorder() {
            System.out.print("Recorrido in-order: ");
            inorderRec(root);
            System.out.println();
        }

    
        private void preorderRec(TreeNode node) {
            if (node != null) {
                System.out.print(node.value + " ");
                preorderRec(node.left);
                preorderRec(node.right);
            }
        }

        public void preorder() {
            System.out.print("Recorrido pre-order: ");
            preorderRec(root);
            System.out.println();
        }

        
        private void postorderRec(TreeNode node) {
            if (node != null) {
                postorderRec(node.left);
                postorderRec(node.right);
                System.out.print(node.value + " ");
            }
        }

        public void postorder() {
            System.out.print("Recorrido post-order: ");
            postorderRec(root);
            System.out.println();
        }

        
        public void delete(int value) {
            TreeNode nodeToDelete = find(value);
            if (nodeToDelete != null) {
                removeTreeNode(nodeToDelete);
            }
        }

        private void removeTreeNode(TreeNode node) {
            
            if (node.left == null && node.right == null) {
                if (node.parent == null) {
                    root = null;
                } else if (node.parent.left == node) {
                    node.parent.left = null;
                } else {
                    node.parent.right = null;
                }
                return;
            }

            
            if (node.left == null || node.right == null) {
                TreeNode child = (node.left != null) ? node.left : node.right;

                if (node.parent == null) {
                    root = child;
                    if (child != null) child.parent = null;
                } else {
                    if (node.parent.left == node) {
                        node.parent.left = child;
                    } else {
                        node.parent.right = child;
                    }
                    if (child != null) child.parent = node.parent;
                }
                return;
            }

            
            TreeNode successor = node.right;
            while (successor.left != null) {
                successor = successor.left;
            }

            
            node.value = successor.value;

            
            removeTreeNode(successor);
        }
    }


    public static void main(String[] args) {

        BinarySearchTree tree = new BinarySearchTree();

        System.out.println("Creando árbol binario de búsqueda");

        int[] values = {8,3,10,1,6,14,4,7,13};

        for(int v : values) {
            tree.insert(v);
        }

        tree.inorder();
        tree.preorder();
        tree.postorder();

        
        System.out.println("Eliminando el valor 6...");
        tree.delete(6);
        tree.inorder();
    }

}