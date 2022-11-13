package lotto;

import lotto.UI.ERRORUI;
import lotto.config.BaseException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Winning {

    private List<Integer> winnings;

    private Integer bonus;

    public void setWinnings(String input) {
        List<Integer> check = new ArrayList<>();
        String[] str = input.split(",");
        check = checkNum(str);
        try {
            if(checkRange(check)){
                winnings = check;
            }
        }catch (IllegalArgumentException e){
            ERRORUI.getErrorRange();
        }
    }

    private List<Integer> checkNum(String[] input){
        List<Integer> num = new ArrayList<>();
        try {
            for (String s : input) {
                num.add(Integer.parseInt(s));
            }
        }catch (IllegalArgumentException e){
            ERRORUI.getErrorInputNum();
        }
        return num;
    }

    private boolean checkRange(List<Integer> check){
        for(Integer integer : check){
            if(integer<1 || integer>45){
                throw  new IllegalArgumentException();
            }
        }
        return true;
    }

    public List<Integer> getWinnings(){
        return winnings;
    }

    public void setBonus(String s){
        Integer check = checkBonusNum(s);
        try {
            if(checkBonusRange(check)){
                bonus = check;
            }
        }catch (IllegalArgumentException e){
            ERRORUI.getErrorRange();
        }
    }

    private Integer checkBonusNum(String s){
        Integer bonus = null;
        try {
            bonus = Integer.parseInt(s);
        }catch (IllegalArgumentException e){
            ERRORUI.getErrorInputNum();
        }
        return bonus;
    }

    private boolean checkBonusRange(Integer bonus){
        if(bonus <1 || bonus >45){
            throw new IllegalArgumentException();
        }
        return true;
    }

    public Integer getBonus(){
        return bonus;
    }

    public List<Integer> getTotalWinning(List<Lotto> user){
        //당첨 통계 갯수 저장소
        List<Integer> countWin = new ArrayList<>(Arrays.asList(0,0,0,0,0));
        for(Lotto lotto: user){
            int sussecc = matchWinningLotto(lotto.getLotto());
            if(sussecc==3){
                countWin.set(0,countWin.get(0)+1);
            }
            if(sussecc==4){
                countWin.set(1,countWin.get(1)+1);
            }
            if(sussecc==5 && !matchBounsLotto(lotto.getLotto())){
                countWin.set(2,countWin.get(2)+1);
            }
            if(sussecc==5 && matchBounsLotto(lotto.getLotto())){
                countWin.set(3,countWin.get(3)+1);
            }
            if (sussecc==6){
                countWin.set(4,countWin.get(4)+1);
            }
        }
        return countWin;
    }
    public Integer matchWinningLotto(List<Integer> lotto){
        int success = 0;
        for(Integer integer : lotto){
            if(compareWinning(integer)) success++;
        }
        return success;
    }

    private boolean compareWinning(int value){
        for(Integer integer : winnings){
            if(integer.equals(value)){
                return true;
            }
        }
        return false;
    }

    public boolean matchBounsLotto(List<Integer> lotto){
        for(Integer integer : lotto){
            if(integer.equals(bonus)) return true;
        }
        return false;
    }
}
