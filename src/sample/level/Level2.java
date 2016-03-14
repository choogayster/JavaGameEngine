package sample.level;

import javafx.scene.shape.Rectangle;
import sample.Enemy;
import sample.EnemyRails;

/**
 * Created by Vlad on 27.02.2016.
 */
public class Level2 extends Level {
    @Override
    public void loadGroundTextures() {
        loadGroundTexture("level2/ground.png");
    }

    @Override
    public void loadWallTextures() {
        for (int i = 1; i <= 30; i++) {
            loadWallTexture("level2/"+i+".png");
        }
    }

    @Override
    public void createEnemyRails() {

        EnemyRails rail = new EnemyRails();
        // Room 1
        /*0*/ rail.addPoint(150, 152);
        /*1*/ rail.addPoint(300, 154);    rail.bindById(0,1);
        /*2*/ rail.addPoint(150, 292);    rail.bindById(0,2);
        /*3*/ rail.addPoint(306, 292);    rail.bindById(1,3); rail.bindById(2,3);
        /*4*/ rail.addPoint(450, 160);    rail.bindById(1,4);
        /*5*/ rail.addPoint(450, 300);    rail.bindById(4,5); rail.bindById(3, 5);
        /*6*/ rail.addPoint(150, 450);    rail.bindById(2,6);
        /*7*/ rail.addPoint(300, 450);    rail.bindById(6,7); rail.bindById(3,7);
        /*8*/ rail.addPoint(455, 460);    rail.bindById(7,8); rail.bindById(5,8);
        // Room 2
        /*9*/ rail.addPoint(1560, 150);
        /*10*/ rail.addPoint(1708, 150);   rail.bindById(9, 10);
        /*11*/ rail.addPoint(1844, 150);   rail.bindById(10, 11);
        /*12*/ rail.addPoint(1563, 274);   rail.bindById(9, 12);
        /*13*/ rail.addPoint(1708, 274);   rail.bindById(13, 12); rail.bindById(13, 10);
        /*14*/ rail.addPoint(1850, 280);   rail.bindById(14, 13); rail.bindById(14, 11);
        /*15*/ rail.addPoint(1558, 430);   rail.bindById(15, 12);
        /*16*/ rail.addPoint(1708, 430);   rail.bindById(16, 15); rail.bindById(16, 13);
        /*17*/ rail.addPoint(1850, 430);   rail.bindById(17, 16); rail.bindById(17, 14);

        // Room 3
        /*18*/ rail.addPoint(160, 1564);
        /*19*/ rail.addPoint(328, 1564);    rail.bindById(19, 18);
        /*20*/ rail.addPoint(448, 1564);    rail.bindById(20, 19);
        /*21*/ rail.addPoint(160, 1704);    rail.bindById(21, 18);
        /*22*/ rail.addPoint(308, 1704);    rail.bindById(22, 21); rail.bindById(22, 19);
        /*23*/ rail.addPoint(448, 1704);    rail.bindById(23, 22); rail.bindById(23, 20);
        /*24*/ rail.addPoint(160, 1854);    rail.bindById(24, 21);
        /*25*/ rail.addPoint(308, 1854);    rail.bindById(25, 24); rail.bindById(25, 22);
        /*26*/ rail.addPoint(448, 1854);    rail.bindById(26, 25); rail.bindById(26, 23);

        // Room 4
        /*27*/ rail.addPoint(1572, 1572);
        /*28*/ rail.addPoint(1700, 1572);    rail.bindById(28, 27);
        /*29*/ rail.addPoint(1832, 1572);    rail.bindById(29, 28);
        /*30*/ rail.addPoint(1572, 1704);    rail.bindById(30, 29);
        /*31*/ rail.addPoint(1700, 1704);    rail.bindById(31, 30); rail.bindById(31, 28);
        /*32*/ rail.addPoint(1832, 1704);    rail.bindById(32, 31); rail.bindById(32, 29);
        /*33*/ rail.addPoint(1572, 1842);    rail.bindById(33, 30);
        /*34*/ rail.addPoint(1700, 1842);    rail.bindById(34, 33); rail.bindById(34, 31);
        /*35*/ rail.addPoint(1832, 1842);    rail.bindById(35, 34); rail.bindById(35, 32);

        // Main Room
        /*36*/ rail.addPoint(610, 457);    rail.bindById(36, 8);
        /*37*/ rail.addPoint(760, 457);    rail.bindById(37, 36);
        /*38*/ rail.addPoint(905, 448);    rail.bindById(38, 37);
        /*39*/ rail.addPoint(1040, 435);    rail.bindById(39, 38);
        /*40*/ rail.addPoint(1200, 435);    rail.bindById(40, 39);
        /*41*/ rail.addPoint(1383, 435);    rail.bindById(41, 40); rail.bindById(41, 15);
        //
        /*42*/ rail.addPoint(420, 585);    rail.bindById(42, 8);
        /*43*/ rail.addPoint(487, 580);    rail.bindById(43, 42); rail.bindById(43, 8); rail.bindById(43, 36);
        /*44*/ rail.addPoint(658, 550);    rail.bindById(44, 36); rail.bindById(44, 37);
        /*45*/ rail.addPoint(820, 560);    rail.bindById(45, 44); rail.bindById(45, 37); rail.bindById(45, 38);
        /*46*/ rail.addPoint(935, 560);    rail.bindById(46, 45); rail.bindById(46, 38);
        /*47*/ rail.addPoint(1040, 505);    rail.bindById(47, 38); rail.bindById(47, 39);
        /*48*/ rail.addPoint(1205, 505);    rail.bindById(48, 47); rail.bindById(48, 40);
        /*49*/ rail.addPoint(1380, 505);    rail.bindById(49, 48); rail.bindById(49, 41);
        //
        /*50*/ rail.addPoint(670, 675);    rail.bindById(50, 44);
        /*51*/ rail.addPoint(822, 675);    rail.bindById(51, 50); rail.bindById(51, 45); rail.bindById(51, 46);
        /*52*/ rail.addPoint(1000, 675);    rail.bindById(52, 51);
        /*53*/ rail.addPoint(1230, 670);    rail.bindById(53, 52);
        /*54*/ rail.addPoint(1420, 670);    rail.bindById(54, 53);
        /*55*/ rail.addPoint(1550, 670);    rail.bindById(55, 54); rail.bindById(55, 15);
        //
        /*56*/ rail.addPoint(420, 770);    rail.bindById(56, 42);
        /*57*/ rail.addPoint(490, 770);    rail.bindById(57, 56); rail.bindById(57, 43);
        /*58*/ rail.addPoint(670, 800);    rail.bindById(58, 50);
        /*59*/ rail.addPoint(830, 800);    rail.bindById(59, 58); rail.bindById(59, 51);
        /*60*/ rail.addPoint(1005, 800);    rail.bindById(60, 59); rail.bindById(60, 52);
        /*61*/ rail.addPoint(1140, 800);    rail.bindById(61, 60);
        /*62*/ rail.addPoint(1280, 800);    rail.bindById(62, 61);
        /*63*/ rail.addPoint(1420, 745);    rail.bindById(63, 54);
        /*64*/ rail.addPoint(1550, 745);    rail.bindById(64, 63); rail.bindById(64, 55);
        //
        /*65*/ rail.addPoint(430, 930);    rail.bindById(65, 56);
        /*66*/ rail.addPoint(490, 930);    rail.bindById(66, 65); rail.bindById(66, 57);
        /*67*/ rail.addPoint(670, 920);    rail.bindById(67, 65); rail.bindById(67, 58);
        /*68*/ rail.addPoint(845, 980);    rail.bindById(68, 66); rail.bindById(68, 59);
        /*69*/ rail.addPoint(1000, 980);    rail.bindById(69, 67); rail.bindById(69, 60); // Evil's source!!!
        /*70*/ rail.addPoint(1140, 980);    rail.bindById(70, 68); rail.bindById(70, 61);
        /*71*/ rail.addPoint(1280, 980);    rail.bindById(71, 69); rail.bindById(71, 62);
        /*72*/ rail.addPoint(1430, 950);
        /*73*/ rail.addPoint(1550, 840);    rail.bindById(73, 64); rail.bindById(73, 63); rail.bindById(73, 72);
        /*74*/ rail.addPoint(1550, 950);    rail.bindById(74, 73); rail.bindById(74, 72);
        //
        /*75*/ rail.addPoint(430, 1080);    rail.bindById(75, 65);
        /*76*/ rail.addPoint(530, 1080);    rail.bindById(76, 75); rail.bindById(76, 66); rail.bindById(76, 67);
        /*77*/ rail.addPoint(620, 1060);    rail.bindById(77, 76);
        /*78*/ rail.addPoint(1280, 1060);   rail.bindById(78, 71);
        /*79*/ rail.addPoint(1440, 1060);   rail.bindById(79, 78); rail.bindById(79, 72);
        /*80*/ rail.addPoint(1550, 1060);   rail.bindById(80, 79); rail.bindById(80, 74);
        //
        /*81*/ rail.addPoint(430, 1190);    rail.bindById(81, 75);
        /*82*/ rail.addPoint(530, 1190);    rail.bindById(82, 81);  rail.bindById(82, 76);
        /*83*/ rail.addPoint(820, 1150);    rail.bindById(83, 68);
        /*84*/ rail.addPoint(1000, 1150);    rail.bindById(84, 83);  rail.bindById(84, 69);
        /*85*/ rail.addPoint(1140, 1150);    rail.bindById(85, 84);  rail.bindById(85, 70);
        /*86*/ rail.addPoint(1280, 1170);    rail.bindById(86, 85);  rail.bindById(86, 78);
        /*87*/ rail.addPoint(1500, 1190);    rail.bindById(87, 80);
        /*88*/ rail.addPoint(1560, 1190);    rail.bindById(88, 87); rail.bindById(88, 80);
        //
        /*89*/ rail.addPoint(430, 1320);    rail.bindById(89, 81);
        /*90*/ rail.addPoint(530, 1320);    rail.bindById(90, 89); rail.bindById(90, 82);
        /*91*/ rail.addPoint(670, 1320);    rail.bindById(91, 90);
        /*92*/ rail.addPoint(850, 1320);    rail.bindById(92, 91);
        /*93*/ rail.addPoint(1000, 1320);    rail.bindById(93, 92); rail.bindById(93, 84);
        /*94*/ rail.addPoint(1140, 1320);    rail.bindById(94, 93); rail.bindById(94, 85);
        /*95*/ rail.addPoint(1280, 1320);    rail.bindById(95, 94); rail.bindById(95, 86);
        /*96*/ rail.addPoint(1500, 1320);    rail.bindById(96, 87);
        /*97*/ rail.addPoint(1570, 1320);    rail.bindById(97, 96); rail.bindById(97, 88);
        //
        /*98*/ rail.addPoint(430, 1420);    rail.bindById(98, 89);
        /*99*/ rail.addPoint(430, 1500);    rail.bindById(99, 98); rail.bindById(99, 20);
        /*100*/ rail.addPoint(610, 1500);    rail.bindById(100, 99);
        /*101*/ rail.addPoint(760, 1500);    rail.bindById(101, 100);
        /*102*/ rail.addPoint(910, 1500);    rail.bindById(102, 101);
        /*103*/ rail.addPoint(1060, 1500);    rail.bindById(103, 102); rail.bindById(103, 94);
        /*104*/ rail.addPoint(1160, 1500);    rail.bindById(104, 103); rail.bindById(104, 94);
        /*105*/ rail.addPoint(1280, 1500);    rail.bindById(105, 104); rail.bindById(105, 95);
        /*106*/ rail.addPoint(1500, 1500);    rail.bindById(106, 105); rail.bindById(106, 96);
        /*107*/ rail.addPoint(1570, 1500);    rail.bindById(107, 106); rail.bindById(107, 97); rail.bindById(107, 27);
        //
        /*108*/ rail.addPoint(610, 1560);    rail.bindById(108, 100); rail.bindById(108, 20);
        /*109*/ rail.addPoint(760, 1560);    rail.bindById(109, 108); rail.bindById(109, 101);
        /*110*/ rail.addPoint(910, 1560);    rail.bindById(110, 109); rail.bindById(110, 102);
        /*111*/ rail.addPoint(1060, 1560);    rail.bindById(111, 110); rail.bindById(111, 103);
        /*112*/ rail.addPoint(1160, 1560);    rail.bindById(112, 111); rail.bindById(112, 104);
        /*113*/ rail.addPoint(1280, 1560);    rail.bindById(113, 112); rail.bindById(113, 105);
        /*114*/ rail.addPoint(1500, 1560);    rail.bindById(114, 113); rail.bindById(114, 106); rail.bindById(114, 27);
        //

        //Green way points
        /*115*/ rail.addPoint(750, 740); rail.bindById(115, 50); rail.bindById(115, 51); rail.bindById(115, 58); rail.bindById(115, 59);
        /*116*/ rail.addPoint(915, 740); rail.bindById(116, 52); rail.bindById(116, 51); rail.bindById(116, 60); rail.bindById(116, 59);
        /*117*/ rail.addPoint(915, 900); rail.bindById(117, 68); rail.bindById(117, 69); rail.bindById(117, 60); rail.bindById(117, 59);
        /*118*/ rail.addPoint(1075, 900); rail.bindById(118, 70); rail.bindById(118, 69); rail.bindById(118, 60); rail.bindById(118, 61);
        /*119*/ rail.addPoint(1215, 900); rail.bindById(119, 70); rail.bindById(119, 71); rail.bindById(119, 62); rail.bindById(119, 61);
        /*120*/ rail.addPoint(900, 1060); rail.bindById(120, 69); rail.bindById(120, 68); rail.bindById(120, 83); rail.bindById(120, 84);
        /*121*/ rail.addPoint(1075, 1060); rail.bindById(121, 69); rail.bindById(121, 70); rail.bindById(121, 85); rail.bindById(121, 84);
        /*122*/ rail.addPoint(1215, 1060); rail.bindById(122, 71); rail.bindById(122, 70); rail.bindById(122, 85); rail.bindById(122, 86);
        /*123*/ rail.addPoint(1060, 1240); rail.bindById(123, 93); rail.bindById(123, 94); rail.bindById(123, 85); rail.bindById(123, 86);
        /*124*/ rail.addPoint(1060, 1240); rail.bindById(124, 95); rail.bindById(124, 94); rail.bindById(124, 87); rail.bindById(124, 86);
        rails.add(rail);
    }

    /*@Override
    public void createEnemies() {
        enemies.add(new Enemy(40, 40, rails.get(0), 69));
        enemies.add(new Enemy(40, 40, rails.get(0), 69));
        enemies.add(new Enemy(40, 40, rails.get(0), 69));
        enemies.add(new Enemy(40, 40, rails.get(0), 69));
        enemies.add(new Enemy(40, 40, rails.get(0), 69));
        enemies.add(new Enemy(40, 40, rails.get(0), 69));
        enemies.add(new Enemy(40, 40, rails.get(0), 69));
        enemies.add(new Enemy(40, 40, rails.get(0), 69));
        enemies.add(new Enemy(40, 40, rails.get(0), 69));
        enemies.add(new Enemy(40, 40, rails.get(0), 69));
        enemies.add(new Enemy(40, 40, rails.get(0), 69));
        enemies.add(new Enemy(40, 40, rails.get(0), 69));
        enemies.add(new Enemy(40, 40, rails.get(0), 69));
        enemies.add(new Enemy(40, 40, rails.get(0), 69));
        enemies.add(new Enemy(40, 40, rails.get(0), 69));
        enemies.add(new Enemy(40, 40, rails.get(0), 69));
        enemies.add(new Enemy(40, 40, rails.get(0), 69));
        enemies.add(new Enemy(40, 40, rails.get(0), 69));
        enemies.add(new Enemy(40, 40, rails.get(0), 69));
        enemies.add(new Enemy(40, 40, rails.get(0), 69));
        enemies.add(new Enemy(40, 40, rails.get(0), 69));
        enemies.add(new Enemy(40, 40, rails.get(0), 69));
        enemies.add(new Enemy(40, 40, rails.get(0), 69));
        enemies.add(new Enemy(40, 40, rails.get(0), 69));
        enemies.add(new Enemy(40, 40, rails.get(0), 69));
        enemies.add(new Enemy(40, 40, rails.get(0), 69));
        enemies.add(new Enemy(40, 40, rails.get(0), 69));
        enemies.add(new Enemy(40, 40, rails.get(0), 69));
        enemies.add(new Enemy(40, 40, rails.get(0), 69));
        enemies.add(new Enemy(40, 40, rails.get(0), 69));
    }*/

    @Override
    public void createWalls() {

        walls.add(new Level.Wall(textureWalls.get(0), 4, 4, new Rectangle(4, 4, 600, 76)));
        walls.add(new Level.Wall(textureWalls.get(1), 4, 91, new Rectangle(4, 80, 76, 444)));
        walls.add(new Level.Wall(textureWalls.get(2), 528, 91, new Rectangle(528, 80, 76, 300)));
        walls.add(new Level.Wall(textureWalls.get(3), 91, 524, new Rectangle(4, 524, 300, 76)));
        walls.add(new Level.Wall(textureWalls.get(4), 604, 304, new Rectangle(604, 304, 868, 76)));
        walls.add(new Level.Wall(textureWalls.get(5), 1396, 4, new Rectangle(1396, 4, 76, 300)));
        walls.add(new Level.Wall(textureWalls.get(6), 1483, 4, new Rectangle(1472, 4, 524, 76)));
        walls.add(new Level.Wall(textureWalls.get(7), 1920, 91, new Rectangle(1920, 80, 76, 520)));
        walls.add(new Level.Wall(textureWalls.get(8), 1624, 524, new Rectangle(1624, 524, 296, 76)));
        walls.add(new Level.Wall(textureWalls.get(9), 1624, 600, new Rectangle(1624, 600, 76, 876)));
        walls.add(new Level.Wall(textureWalls.get(10), 1700, 1400, new Rectangle(1700, 1400, 296, 76)));
        walls.add(new Level.Wall(textureWalls.get(11), 1920, 1487, new Rectangle(1920, 1476, 76, 520)));
        walls.add(new Level.Wall(textureWalls.get(12), 1396, 1920, new Rectangle(1396, 1920, 524, 76)));
        walls.add(new Level.Wall(textureWalls.get(13), 1396, 1624, new Rectangle(1396, 1624, 76, 296)));
        walls.add(new Level.Wall(textureWalls.get(14), 528, 1624, new Rectangle(528, 1624, 864, 76)));
        walls.add(new Level.Wall(textureWalls.get(15), 528, 1700, new Rectangle(528, 1700, 76, 296)));
        walls.add(new Level.Wall(textureWalls.get(16), 4, 1920, new Rectangle(4, 1920, 576, 76)));
        walls.add(new Level.Wall(textureWalls.get(17), 4, 1400, new Rectangle(4, 1400, 76, 520)));
        walls.add(new Level.Wall(textureWalls.get(18), 91, 1400, new Rectangle(80, 1400, 300, 76)));
        walls.add(new Level.Wall(textureWalls.get(19), 304, 524, new Rectangle(304, 524, 76, 876)));
        walls.add(new Level.Wall(textureWalls.get(20), 528, 584, new Rectangle(528, 584, 76, 292)));
        walls.add(new Level.Wall(textureWalls.get(21), 1000, 548, new Rectangle(1000, 548, 468, 76)));
        walls.add(new Level.Wall(textureWalls.get(22), 1376, 1124, new Rectangle(1376, 1124, 76, 292)));
        walls.add(new Level.Wall(textureWalls.get(23), 528, 1360, new Rectangle(528, 1360, 468, 76)));

        walls.add(new Level.Wall(textureWalls.get(24), 680, 1000, new Rectangle(680, 1000, 40, 240)));
        walls.add(new Level.Wall(textureWalls.get(25), 604, 1124, new Rectangle(604, 1124, 76, 84)));
        walls.add(new Level.Wall(textureWalls.get(26), 680, 1240, new Rectangle(680, 1240, 240, 40)));
        walls.add(new Level.Wall(textureWalls.get(27), 1132, 712, new Rectangle(1132, 712, 200, 40)));
        walls.add(new Level.Wall(textureWalls.get(28), 1332, 712, new Rectangle(1332, 712, 40, 280)));
        walls.add(new Level.Wall(textureWalls.get(29), 1383, 800, new Rectangle(1372, 800, 76, 76)));

    }

    @Override
    public void createGrounds() {

        grounds.add(new Level.Ground(textureGrounds.get(0), 0, 0));

    }
}
