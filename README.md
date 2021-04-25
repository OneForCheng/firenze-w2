# 德州扑克规则

<br />

主要包含流程执行和奖金分配的规则：

<br />

#### 流程执行

##### 玩家在四轮中分别投注，投注金额进入底池

例如：玩家 A、B、C、D，所有人都选择跟注，最终底池总金额为160

| 玩家 | 翻牌前 | 翻牌 | 转牌 | 河牌 |
| ---- | ------ | ---- | ---- | ---- |
| A    | 10     | 10   | 10   | 10   |
| B    | 10     | 10   | 10   | 10   |
| C    | 10     | 10   | 10   | 10   |
| D    | 10     | 10   | 10   | 10   |

<br />

##### 玩家在任一轮次中放弃则退出游戏，投入底池的资金从筹码中扣除

例如：玩家D和C分别在第一轮和第二轮弃牌退出游戏，最终底池总金额为90

| 玩家 | 翻牌前 | 翻牌 | 转牌 | 河牌 |
| ---- | ------ | ---- | ---- | ---- |
| A    | 10     | 10   | 10   | 10   |
| B    | 10     | 10   | 10   | 10   |
| C    | 10     | 弃牌 |      |      |
| D    | 弃牌   |      |      |      |

<br />

##### 在最后一轮前，如果只剩一位玩家，则该玩家成为赢家，获得底池内所有奖金

例如：玩家D、C、B分别在第一轮、第二轮和第三轮弃牌退出游戏，玩家A将作为赢家获得底池中所有的奖金60

| 玩家 | 翻牌前 | 翻牌 | 转牌 | 河牌 |
| ---- | ------ | ---- | ---- | ---- |
| A    | 10     | 10   | 10   |      |
| B    | 10     | 10   | 弃牌 |      |
| C    | 10     | 弃牌 |      |      |
| D    | 弃牌   |      |      |      |

<br />

##### 直到最后一轮投注结束，仍未退出的玩家进入摊牌阶段

例如：经过四轮，最终还剩下A、B两位玩家进入摊牌阶段

| 玩家 | 翻牌前 | 翻牌 | 转牌 | 河牌 |
| ---- | ------ | ---- | ---- | ---- |
| A    | 10     | 10   | 10   | 10   |
| B    | 10     | 10   | 10   | 10   |
| C    | 10     | 弃牌 |      |      |
| D    | 弃牌   |      |      |      |

<br />

##### 摊牌阶段，仍未退出的玩家从手牌与河牌挑选最大的组合，进行比较

例如：经过四轮，最终还剩下A、B、C三位玩家进入摊牌阶段

<table>
    <tr>
        <td colspan="4" style="text-align:center;">
          公牌
          <br/>
					4♣ K♠ <span style="color:red;">4♥</span> 8♠ 7♠
        </td> 
   </tr>
    <tr>
        <td style="text-align:center;">
      		A<br>
          A♣ <span style="color:red;">4♦</span>
      	</td>  
        <td style="text-align:center;">
      		B<br>
          A♠ 9♠
      	</td>
        <td style="text-align:center;">
      		C<br>
          <span style="color:red;">K♥ K♦</span>
      	</td> 
        <td style="text-align:center;">
      		D<br>
          弃牌
      	</td> 
    </tr>
</table>

每个玩家以可用的七张牌中选五张组成最大的牌型：

<table>
    <tr>
        <td style="text-align:center;">
      		A
      	</td>  
        <td style="text-align:center;">
      		4♣ <span style="color:red;">4♥ 4♦</span> A♣ K♠
      	</td>
        <td style="text-align:center;">
      		三条4、次大牌为A-K
      	</td> 
    </tr>
   <tr>
        <td style="text-align:center;">
      		B
      	</td>  
        <td style="text-align:center;">
      		A♠ K♠ 9♠ 8♠ 7♠
      	</td>
        <td style="text-align:center;">
      		同花
      	</td> 
    </tr>
   <tr>
        <td style="text-align:center;">
      		C
      	</td>  
        <td style="text-align:center;">
      		K♠ <span style="color:red;">K♥ K♦</span> 4♣ <span style="color:red;">4♥</span>
      	</td>
        <td style="text-align:center;">
      		K葫芦搭一对4
      	</td> 
    </tr>
</table>

C的葫芦最大，B次之，A最小

<br />

#### 奖金分配

##### 如有一人胜出，则该玩家成为赢家，获得底池内所有奖金

例如：玩家A、B、C、D的投注如下，并且最终A的牌型大于B的，那么A将获得所有的金额90元

| 玩家 | 翻牌前 | 翻牌 | 转牌 | 河牌 |
| ---- | ------ | ---- | ---- | ---- |
| A    | 10     | 10   | 10   | 10   |
| B    | 10     | 10   | 10   | 10   |
| C    | 10     | 弃牌 |      |      |
| D    | 弃牌   |      |      |      |

<br />

##### 如平局，则赢家平分底池内所有奖金

例如：玩家A、B、C、D的投注如下，并且最终公开牌为最大牌型，那么A和B将平分池底的90元

| 玩家 | 翻牌前 | 翻牌 | 转牌 | 河牌 |
| ---- | ------ | ---- | ---- | ---- |
| A    | 10     | 10   | 10   | 10   |
| B    | 10     | 10   | 10   | 10   |
| C    | 10     | 弃牌 |      |      |
| D    | 弃牌   |      |      |      |

