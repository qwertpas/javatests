import java.util.ArrayList;

class PassByReference {


    ArrayList<Group> group1List = new ArrayList<Group>();

    public static void main(String[] args) {

        PassByReference myself = new PassByReference();
        
        myself.createGroups();
        myself.setGroupThings(1.2, 1.2);

        System.out.println("group1thing1: " + myself.group1List.get(0).thing1);
        System.out.println("group1thing2: " + myself.group1List.get(0).thing2);

    }

    void createGroups(){
        Group group1 = new Group(1, 2);
        group1.setthing1(1.1);
        group1.setthing2(2.1);
        group1List.add(group1);

        Group group2 = new Group(3, 4);
        group1.setthing1(3.1);
        group1.setthing2(4.1);
        group1List.add(group2);
    }

    void setGroupThings(double thing1, double thing2){
        for(Group group : group1List){
            group.setthing1(thing1);
            group.setthing2(thing2);
        }
    }


    public static class Group{
        double thing1;
        double thing2;
        Group(double thing1, double thing2){
            this.thing1 = thing1;
            this.thing2 = thing2;
        }
        void setthing1(double thing1){
            this.thing1 = thing1;
        }
        void setthing2(double thing2){
            this.thing2 = thing2;
        }
    }

}

