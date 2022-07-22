import org.junit.Test;

public class SimpleFactory {


    static class Animal{
        private String name;

        private Animal buildAnimal(String name){
            Animal animal = new Animal();
            animal.name = name;
            return animal;
        }
    }

    static class AnimalFactory{

        public static Animal getAnimal(String type){
            Animal animal;
            switch (type){
                case "1":
                    animal = new Animal().buildAnimal("cat");
                    break;
                case "2":
                    animal = new Animal().buildAnimal("dog");
                    break;
                default:
                    animal = new Animal().buildAnimal("beet");
                    break;
            }
            return animal;
        }

    }



    @Test
    public void test(){

        Animal animalInfo = AnimalFactory.getAnimal("2");
        System.out.println(animalInfo.name);
    }


}
