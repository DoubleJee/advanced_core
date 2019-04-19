package design_pattern;

public class Core {
    /**
     * 设计原则：
     *  1：分开不变和变化的部分，封装变化！！    （找出应用中可能需要变化之处，并把他们独立出来，不要和那些不变化的代码混在一起）
     *  2：面向接口编程，而不是面向实现编程      （接口 x = new 实现();）
     *  3：多用组合，少用继承，                  （鸭子类里组合了各种行为组合了，飞和叫 )
     *  4：为了对象之间的松耦合设计而努力         （修改主题不会影响观察者，修改观察者不会影响主题，他们之间以遵循的接口来联系，我们可以自由改变他们）
     *  5：开闭原则，对修改关闭，对扩展开放       （装饰者模式，可以无限在类行为扩展，装饰，而不修改类）
     *  6：依赖倒置原则：要依赖抽象，不要依赖具体类 (披萨店依赖一个抽象的披萨，这个抽象的披萨实际由客户选择的披萨店子类，来决定真正要的具体实现披萨)
     *  7：最少知识原则：只和你的密友谈话，只有一个朋友（如果某对象是调用其他方法的返回结果，不要调用该对象的方法。调用其他方法得到的对象，不要调用该对象的方法。被当做方法的参数传进来的对象和此方法创建的对象，可以调用。）
     *  8：好莱坞原则：别调用我们，我们会调用你
     *  9：单一职责：一个类应该只有一个引起变化的原因 （迭代器模式，菜单只做存储菜单，遍历的事情交给迭代器）
     */

    /**
     *     内聚：它用来度量一个类或者模块紧密的达到单一目的或责任。
     *     当一个模块或一个类被设计成只支持一组相关的功能时，我们说它具有高内聚。
     */
}
