
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Admin
 */
public class Bees {
    String name;
    float health;
    float dead;
    String status;
    public Bees()
    {
        Random rd=new Random();
        int index=rd.nextInt(3);
        this.name=typeName(index);
        this.dead=typeDead(index);
        status="ALIVE";
        health=100;
    }

    public Bees(String name, float health, float dead, String status) {
        this.name = name;
        this.health = health;
        this.dead = dead;
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getHealth() {
        return health;
    }

    public void setHealth(float health) {
        this.health = health;
    }

    public float getDead() {
        return dead;
    }

    public void setDead(float dead) {
        this.dead = dead;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    public String typeName(int index)
    {
        String[] name= {"WORKER", "QUEEN", "DRONE"};
        return name[index];
    }
    
    public int typeDead(int index)
    {
        int[] dead={70, 20, 50};
        return dead[index];
    }
    public void damage(int n)
    {
        health-=n;
        if (health<0) health=0;
        if (health < dead)
        {
            status="DEAD";
        }
    }

    @Override
    public String toString() {
        return ""+name+" | "+health+" | "+status; 
        //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
