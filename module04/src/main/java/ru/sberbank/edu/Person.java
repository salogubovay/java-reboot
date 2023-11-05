package ru.sberbank.edu;

/**
 * Класс {@code Person} реализует интерфейс Comparable<Person>
 * @author Александр Салогубов
 *
 */
public class Person implements Comparable<Person>{
    /**
     * Имя человека
     */
    private String name;
    
    /**
     * Название города
     */
    private String city;
    
    /**
     * Возраст человека
     */
    private int age;
    
    /**
     * @param name - имя
     * @param city - город
     * @param age - возраст
     */
    Person(String name, String city, int age){
        if (name == null || city == null) {
            throw new IllegalArgumentException("Incorrect arguments: name = " + name + "; city = " + city + ";");
        }
        this.name = name;
        this.city = city;
        this.age = age;
    }
    
    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
    }

    public int getAge() {
        return age;
    }
    
    @Override
    public int hashCode() {
        int prime = 103;
        int hash = prime + name.toLowerCase().hashCode();
        hash = hash * prime + city.toLowerCase().hashCode();
        hash = hash * prime + age;
        return hash;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (this.getClass() != o.getClass()) return false;
        Person p = (Person) o;
        return name.toLowerCase().equals(p.name.toLowerCase())
                && city.toLowerCase().equals(p.city.toLowerCase()) 
                && age == p.age;
    }
    
    @Override
    public int compareTo(Person o) {
        if (city.toLowerCase().equals(o.city.toLowerCase()) && name.toLowerCase().equals(o.name.toLowerCase())) {
            return 0;
        } else if (city.toLowerCase().equals(o.city.toLowerCase())) {
            return name.compareToIgnoreCase(o.name);
        } else {
            return city.compareToIgnoreCase(o.city);
        }
    }
    
    public String toString() {
        return "name = " + name + "; city = " + city + "; age = " + age + ";";
    }

}
