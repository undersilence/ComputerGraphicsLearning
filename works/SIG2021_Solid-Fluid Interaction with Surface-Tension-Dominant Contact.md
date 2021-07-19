# Solid-Fluid Interaction with Surface-Tension-Dominant Contact

## Background 背景介绍

+ The most salient feature of such a strongly coupled solid-fluid system is the feasibility of holding an object on a liquid surface whose density is significantly higher than the fluid underneath

这种强耦合的固体-流体系统最显著的特征是将物体保持在液体表面上的可行性，该液体表面的密度明显高于下面的流体

+ From a computational perspective, accurately modeling the interaction among these three forces requires proper treatment of three subsystems — the liquid body, the solid body, and the strongly tensioned liquid interface between them.

从计算的角度来看，精确的模拟这三种力之间的相互作用需要正确的处理三个子系统，液体、固体、和它们之间的强张力液体表面。

## Contribution 贡献点

+ A novel Lagrangian thin membrane representation to accurately capture the  contacting interactions between solid and fluid driven by strong surface  tension.

一种创新的拉格朗日薄膜表示方法，用于精确的捕捉由强表面张力驱动的固体和流体之间的接触相互作用。

+ A monolithic coupling framework satisfying all velocity and position constraints  while conserving momentum.

一个整体的耦合框架，同时满足所有速度以及位置约束，同时保持动量守恒

+ A prediction-correction contact handling scheme to accurately process  fluid-solid contact with realistic physical parameters.

可以使用真实物理参数精确处理流固接触的预测矫正处理方案

## Physical Model 物理模型

###  Volumetric Fluid 流体

遵守标准不可压缩流的 纳维斯托克斯 方程

![image-20210719215531545](D:\Workspace\ComputerGraphicsLearning\works\figs\navier-stokes.png)

### Surface membrane 表面薄膜

+ define the geometry of the surface membrane as the thin region around the  fluid’s free surface with a constant thickness ℎ

将表面膜的几何形状定义为流体自由表面周围的薄区域，并且有恒定的厚度 ℎ

+ The governing equations of the fluid membrane are the same as the fluid volume,  except that a surface tension force is applied on the membrane due to the  unbalanced cohesion force between the fluid molecules near the interface.

控制方程与流体部分相同，另外由于界面附近流体分子之间的不平衡内聚力而在膜上产生了表面张力

从毛细力的计算角度：
$$
\mathrm{f}_{c}=\oint_{\Gamma} \sigma \mathrm{d} \mathrm{l} \times \mathrm{n}
$$
文章最后使用的是能量形式 $ E_c = \sigma A$ ，其中 $A$ 为表面的总面积。表面薄膜在计算时会同时与液体和刚体交换动能

### Rigid body 刚体

推广的牛顿第二定律 $F = ma$
$$
\mathrm{M}_{r}\left(\mathrm{q}_{r}\right) \frac{\mathrm{d} v_{r}}{\mathrm{~d} t}=\mathrm{f}_{r}\left(\mathrm{q}_{r}, \mathrm{v}_{r}\right)
$$

## Discretization 离散化

+ 对于流体离散为基本的 Poisson equation
+ 对于薄膜，离散为两倍背景网格精度的三角网格，1.5x或3x也work并且不会有显著的行为差异



## Three-way Coupling 三路耦合

### Fluid-Membrane Coupling

### Membrane-Rigid Coupling

### Three-way Coupling





