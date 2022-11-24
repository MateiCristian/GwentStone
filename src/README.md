

# Tema POO  - GwentStone

<div align="center"><img src="https://tenor.com/view/witcher3-gif-9340436.gif" width="500px"></div>

## Explicatii suplimentare
 Am folosit clasa Main pentru a crea parserul in functie de comanda data, iar in functie de 
comanda am realizat rezolvarea taskului ori in main, ori intr-o clasa special creata pentru acel task.

## Ierarhia claselor:
### Clase pentru carti:

Clasa Cards - este o clasa comuna ce are toate atributele comune cartilor de tip
Environment si Minion. <br><br>
Clasa Environment - extinde clasa Cards si contine cativa constructori/ getteri si setteri. <br><br>
Clasa Minion - extinde clasa Cards si mai are in plus cateva campuri specifice acestui tip de carte:
health, attackDamage, frozen si attackedtur (health reprezinta viata unui minion,
attackDamage este puterea de atac, frozen reprezinta starea cartii, daca poate fi folosita
sau nu in aceasta tura si attackedtur retine o valoarea de 0 sau de 1, ce reprezinta
daca cartea a atacat deja in aceasta tura). <br> 

### Clase pentru joc:
Clasa arena - implementeaza arena de joc si este o matrice reprezentata printr-un ArrayList de
ArrayList-uri. <br><br>
Clasa Playerv2 - este o clasa specifica pentru jucatori, aceasta va avea 2 instante principale,
una pentru primul jucator si una pentru al doilea jucator. Clasa contine urmatoarele campuri:
nrCardsInDeck (numarul de carti din deck), deck (ArrayList de carti ce reprezinta deckul playerilor),
hand (ArrayList de carti ce reprezinta cartile din mana unui player), hero (o instanta a clasei Hero),
mana ce reprezinta mana playerului. <br> <br>
Clasa Hero - reprezinta eroul unui jucator si extinde clasa Cards, in plus mai are si campurile
health ce reprezinta viata eroului si attackedtur ce poate avea valori de 0 sau 1 (daca eroul a atacat
deja in runda curenta).

### Clase pentru implementare:
Clasa AddDeck - ce contine doar o metoda statica adddeck, cu parametrii finali: player, inputData,
idxplayer, nrplayer si adauga deckul cu indexul idxplayer in deckul playerului nrplayer
(daca nrplayer este 1 atunci adauga la player 1, iar daca nrplayer este 2 atunci adauga la player 2). <br>
<br>
Clasele Coord si Coordinates - sunt coordonatele unei carti reprezentate de un x si un y (x pentru 
linie si y pentru coloana).<br><br>
Clasa CardUsesAbility - contine doar o metoda statica cardUsesAbility cu parametrii finali inputData,
output, arena, i, j si implementeaza folosirea unui abilitati a unei carti din arena jocului, de la 
linia i si coloana j. <br><br>
Clasa UseAttackHero - contine doar o metoda statica useAttackHero cu paramerii finali inputData, output,
arena, i, j, player1 si player2. Aceasta metoda implementeaza atacul unui erou, eroul fiind atacat de 
cartea ce se afla in arena jocului pe linia i si coloana j. <br><br>
Clasa UseEnvironment - contine doar o metoda statica useEnvironment cu parametrii finali arena, card,
affectedrow, player1 si player2. Aceasta metoda implementeaza folosirea unei carti de tip spell (environment) 
verificand toate conditiile prezentate in enunt.<br><br>
Clasa UseHeroAbility - contine doar o metoda statica useHeroAbility cu parametrii finali inputData,
output, arena, affectedrow, player, pidx (ce reprezinta indexul playerului, 1 pentru primul jucator si
2 pentru al doilea jucator). Aceasta metoda implementeaza folosirea puterii unui erou, verificand 
toate conditiile din enunt.

### Clase pentru afisare:
Clasele AddPojoattacker si AddPojocoordinates au fost facute pentru a putea crea mai usor outputul de 
tip json pentru anumite taskuri.