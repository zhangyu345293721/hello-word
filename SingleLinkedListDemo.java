package DataStructures;

/**
 * @author: ln
 * @data: 2022/7/14
 * @description:创建单向链表和遍历
 */
public class SingleLinkedListDemo {
    public static void main(String[] args) {
        //测试
        //先创建结点
        HeroNode hero1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode hero2 = new HeroNode(2, "卢俊义", "玉麒麟");
        HeroNode hero3 = new HeroNode(3, "吴用", "智多星");
        HeroNode hero4 = new HeroNode(4, "林冲", "豹子头");

        //创建链表
        SingleLinkedList singleLinkedList = new SingleLinkedList();

        //加入
        singleLinkedList.add(hero1);
        singleLinkedList.add(hero2);
        singleLinkedList.add(hero3);
        singleLinkedList.add(hero4);

        System.out.println("原来链表的情况。");
        singleLinkedList.list();

//        System.out.println("链表反转后。。");
//        reverseList(singleLinkedList.getHead());
//        singleLinkedList.list();


/*
        singleLinkedList.addByOrder(hero1);
        singleLinkedList.addByOrder(hero4);
        singleLinkedList.addByOrder(hero2);
        singleLinkedList.addByOrder(hero3);

        singleLinkedList.list();

        //测试修改功能
        HeroNode newHeroNode = new HeroNode(2, "小卢", "玉麒麟!!!");
        singleLinkedList.update(newHeroNode);
        System.out.println("修改链表结点信息后");
        singleLinkedList.list();

//        //测试删除功能
        System.out.println("删除后的链表");
        singleLinkedList. delete(1);
        singleLinkedList. delete(4);

        singleLinkedList.list();

        System.out.println("链表中还有有效节点个数为:" + getLength(singleLinkedList.getHead()));

        //测试得到倒数第K个节点
        HeroNode res = finLastIndexNOde(singleLinkedList.getHead(),1);
        System.out.println(res);
*/
        //显示
//        singleLinkedList.list();
    }
}

//定义SingleLinkedList
class SingleLinkedList {
    //先初始化一个头节点
    private HeroNode head = new HeroNode(0,"","");

    //返回头节点
    public HeroNode getHead() {
        return head;
    }

    //添加结点到单向链表
    //思路,当不考虑编号顺序时
    //1.找到当前链表的最后结点
    //2.将最后这个结点的next指向新的结点
    public void add(HeroNode heroNode) {

        //因为head不能动，因此我们需要辅助变量temp
        HeroNode temp = head;
        //遍历链表找到最后
        while (true) {
            //找到链表的最后
            if (temp.next == null) {
                break;
            }
            //如果没有找到，将temp后移动
            temp = temp.next;
        }
        //当退出while循环时，temp就指向链表的最后
        temp.next = heroNode;

    }

    //按顺序插入结点
    public void addByOrder(HeroNode heroNode) {
        //定义辅助指针
        HeroNode temp = head;
        boolean flag = false;//表志要添加的是否存在
        while (true) {
            if (temp.next == null) {
                break;
            }
            if (temp.next.no > heroNode.no) {//找到插入点
                break;
            } else if (temp.next.no == heroNode.no) {//说明希望添加的heroNode的编号已经存在
                flag = true;//说明编号存在
                break;
            }
            temp = temp.next;//后移，遍历当前链表
        }
        //判断flag的值
        if (flag) {//不能添加说明编号存在
            System.out.printf("准备插入的英雄的编号%d已经存在，不能加入\n",heroNode.no);
        } else {
            //插入到链表中
            heroNode.next = temp.next;
            temp.next = heroNode;
        }

    }

    //修改结点信息
    public void update(HeroNode newHeroNode) {
        //判断是否为空链表
        if (head.next == null) {
            System.out.println("链表为空。。");
            return;
        }
        HeroNode temp = head.next;
        boolean flag = false;//表示是否找到节点
        while (true) {
            if (temp == null) {
                break;  //表示到链表尾部
            }
            if (temp.no == newHeroNode.no) {
                //找到
                flag = true;
                break;
            }
            temp = temp.next;
        }
        //根据flag判断是否找到要修改的结点
        if (flag) {
            temp.name = newHeroNode.name;
            temp.nickname = newHeroNode.nickname;
        } else {    //没有找到
            System.out.printf("没有找到编号 %d 的节点，不能修改\n",newHeroNode.no);
        }
    }

    //删除结点
    //思路:1.head不能动，因此我们需要一个temp辅助节点找到待删除的前一个节点
    //    2.我们在比较时，时temp.next.no 和需要删除的节点no比较
    public void delete(int no) {
        HeroNode temp = head;
        boolean flag = false;
        //判断链表是否为空

        //遍历查找要修改的节点
        while (true) {
            if (temp.next == null) {//到链表尾部
                break;
            }
            if (temp.next.no == no) {   //找到前一个节点
                flag = true;
                break;
            }
            //指针后移
            temp = temp.next;
        }
        //
        if (flag) {
            //找到，删除
            temp.next = temp.next.next;
        } else {
            System.out.printf("要删除的节点 %d 不存在!!!",no);
        }
    }

    //查找单链表的倒数第k个节点
    //1.编写一个方法接收head节点，同时接收一个index
    //2.index表示是倒数第index个节点
    //3.先把链表从头到尾遍历，得到链表总的长度getLength
    //4.得到size后，我们从链表的第一个开始遍历（size - index）个，就可以得到
    //5.如果找到则返回节点，否则放回空
    public  static HeroNode finLastIndexNOde(HeroNode head, int index) {
        //判断链表如果为空，返回null
        if (head.next == null) {
            return null;//没有找到
        }
        //第一个遍历得到链表的长度(节点个数)
        int size = getLength(head);
        //第二遍遍历size - index位置，就是我们要找的倒数第k个节点
        //先做一个index的检验
        if (index <= 0 || index > size) {
            return null;
        }
        //定义辅助变量，for循环定位到index
        HeroNode cur = head.next;
        for (int i = 0; i < size - index; i++) {
            cur = cur.next;
        }
        return cur;
    }


    //方法:获取单链表的节点的个数（如果待头节点，需求不统计头节点）
    /**
     *
     * @param head 链表的头节点
     * @return 返回的就是有效节点的个数
     */
    public static int getLength(HeroNode head) {
        if (head.next == null) {//空链表
            return 0;
        }
        int length = 0;
        //定义辅助变量
        HeroNode cur = head.next;
        while (cur != null) {
            length++;
            cur = cur.next;
        }
        return length;
    }

    //将单链表反转
    public static void reverseList(HeroNode head) {
        //如果为空，直接返回
        if (head.next == null || head.next.next == null) {
            return;
        }
        //定义一个辅助的指针（变量），帮助我们遍历原来的链表
        HeroNode cur = head.next;
        HeroNode next = null;//指向当前节点[cur]的下一个节点
        HeroNode reverseHead = new HeroNode(0,"","");
        //遍历原来的链表，每遍历一遍，就将其取出，并放在新的链表reverseHead的最前端
        while (cur != null) {
            next = cur.next;//先暂时保存当前节点的下一个节点
            cur.next = reverseHead.next;//将cur的下一个节点指向新的链表的最前端
            reverseHead.next = cur;
            cur = next;//cur的向后偏移
        }
        //将head.next 指向reverseHead.next,实现单链表的反转
        head.next = reverseHead.next;
    }



    //显示链表【遍历】
    public void list() {
        //判读是否为空
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        //因为头节点不能动，因此需要一个辅助变量来遍历
        HeroNode temp = head.next;
        while (true) {
            if (temp == null) {
                break;
            } else {
                System.out.println(temp);
                temp = temp.next;
            }
        }

    }




}

class HeroNode {
    public int no;
    public String name;
    public String nickname;
    public HeroNode next;

    public HeroNode(int no, String name, String nickname) {
        this.no = no;
        this.name = name;
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}
