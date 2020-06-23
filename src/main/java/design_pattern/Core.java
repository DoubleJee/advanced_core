package design_pattern;

public class Core {
    /**
     * 设计原则：
     *  1：分开不变和变化的部分，封装变化！！     （找出应用中可能需要变化之处，并把他们独立出来，不要和那些不变化的代码混在一起）
     *  2：面向接口编程，而不是面向实现编程       （接口 x = new 实现();)，不依赖具体实现，比如几何形状，你不用去关心它是圆形，还是正方形，还是尚未定义的形状，把它们都当作是几何来对待，方法操作都是泛化，所有的几何形状都可以绘制、移动。这些方法都是直接对一个几何对象发送消息，它不用担心对象将如何处理消息。这样的代码是不会受到添加新类型影响的。
     *  3：多用组合，少用继承，                  （鸭子类里组合了各种行为组合了，飞和叫 )
     *  4：为了对象之间的松耦合设计而努力         （修改主题不会影响观察者，修改观察者不会影响主题，他们之间以遵循的接口来联系，我们可以自由改变他们）
     *  5：开闭原则，对修改关闭，对扩展开放       （装饰者模式，可以无限在类行为扩展，装饰，而不修改类）
     *  6：依赖倒置原则：要依赖抽象，不要依赖具体类 (披萨店依赖一个抽象的披萨，这个抽象的披萨实际由客户选择的披萨店子类，来决定真正要的具体实现披萨)
     *  7：最少知识原则：只和你的密友谈话，只有一个朋友（如果某对象是调用其他方法的返回结果，不要调用该对象的方法。调用其他方法得到的对象，不要调用该对象的方法。被当做方法的参数传进来的对象和此方法创建的对象，可以调用。）
     *  8：好莱坞原则：别调用我们，我们会调用你    （你调用的人会通知你，client调用server之后，server会通知你，去除轮询，用回调通知方式）
     *  9：单一职责：一个类应该只有一个引起变化的原因 （迭代器模式，菜单只做存储菜单，遍历的事情交给迭代器）
     */

    /**
     *     内聚：它用来度量一个类或者模块紧密的达到单一目的或责任。
     *     当一个模块或一个类被设计成只支持一组相关的功能时，我们说它具有高内聚。
     *
     *     每个对象都可以很好的完成一项功能，但是他并不试图做更多的事情
     */

    /**
     *     解耦：要依赖抽象，不要依赖具体类。面向接口编程，而不是面向实现编程
     *     请求者和实现者分离，请求者使用统一的抽象接口，具体实现分离开，
     *     请求者 -> 抽象方法 -> 实现者 不需要修改客户端代码，需要修改的是实现者
     *
     *     如：我们去请求一个服务器依赖一个IP+端口，依赖了具体实现，那如果服务器换了怎么办？我们要改写Ajax里的url吗？
     *     增加一个域名，我们不知道域名具体指向哪个IP。
     *     所以我们没有直接确定IP+端口，没有依赖具体真正的哪台服务器，而是依赖了域名，做修改的话直接修改域名对应的服务器就好了。
     *     在我们来看没有真正明确到IP和端口（目标），做了泛化解耦。
     *
     *     依赖、使用一个抽象，它能有无限种可能，一旦依赖实现，它就只有一种可能
     */

    /**
     *  行为与实现分离：
     *  定义接口规定行为，调用者依赖接口的行为，而不是直接在调用者代码里写实现代码。
     *  将实现代码抽离出来形成接口，调用者依赖抽象接口，不依赖实际代码。 也算是面向抽象、依赖抽象
     */

    /**
     * 模式：在某种情境，针对某问题的某种解决方案
     * 情境 就是应用某个模式的情况。这应该是会不断出现的情况，列如：你有一个对象的集合
     * 问题 就是你想在某情境下达到目标，但也是某情境下的约束  列如：你需要注意走访每个对象，而不需要厉害该集合的实现
     * 解决方案 就是你所追求的：一个通用的设计，用来解决约束达到目标  将迭代封装进分离的类中
     */

    /**
     * 对象导论：把对象当成服务提供者。提供消息给对象，让他完成某件事。
     *  每一个可能的请求（消息）都有一个方法与之相关联，如：y.xx()，向y对象发送消息，调用方法处理消息，完成某件事。
     *
     *  对象不只是人类、动物，一切皆对象！一个构造器、一个处理者、一个规律计算器、甚至是一个零件、他们都有自己的特征和行为，这些统统都是对象。
     */

    /**
     * 封装：是为了不希望调用者访问不必要的、或者不想他们关注的、不允许他们操作的东西
     * 比如一个属性。你只希望人家获取它不能修改它，设为私有的，提供get方法。
     * 抽象出来，进行封装
     */
}
