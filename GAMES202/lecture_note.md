# GAMES 202 课程关键笔记

## Lecture 0

这一次，好好学渲染。

## Lecture 1

### Graphics Pipeline

Vertex Processing -> Triangle Processing -> Rasterization -> Fragment Processing -> Framebuffer Operations

shader语言的层级关系：CG语言 -> GLSL&HLSL -> GPU汇编

### 怎么调试Shader? 
1. 朴实版：将需要调试的值变换到 0~1 的范围，通过颜色输出后利用取色工具获取rgb来进行值的判断
2. 实用版：使用 Nvidia Nsight, **RenderDoc**（<- 以后重点用一用） 等工具进行调试

## Lecture 2  Real-time Shadows 1

$$
\int_{\Omega}{f(x)g(x)\mathrm{d}x} \approx \frac{\int_\Omega f(x)\mathrm{d}x}{\int_\Omega \mathrm{d}x}\int_{\Omega}{g(x)\mathrm{d}x}
$$

近似思想，近似等式在 积分域比较小 或 g(x) 光滑的情况下成立，总而言之就是能强行近似。

### Percentage closer soft shadows (PCSS)

Percentage Closer Filtering (PCF) : 本用来实现抗锯齿，在PCSS中不是对 **结果 和 SM** 进行 Filtering

对于摄像机中每一个像素，与SM中一块区域内的深度进行比较并求（加权）平均得出可见度。需要注意的是比较区域的大小取决于 遮挡物 和 像素点 的距离 blocker distance。

