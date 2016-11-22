package attakers;

import java.util.Stack;

public class Battle {
    Stack<AttackbleEntity> battleStack=new Stack<AttackbleEntity>();
    public void addEntity(AttackbleEntity entity){
        battleStack.push(entity);
    }
    public AttackbleEntity getWiner(){
        AttackbleEntity winer=battleStack.pop();
        while(!battleStack.empty())winer=winer.doAttack(battleStack.pop());
        return winer;
    }
}
