В этом примере показано, как создать переключатель светлой/темной темы.

Серверная часть компонента находится в [`ThemeToggle.java`]({currentPath}?tab=ThemeToggle.java). Она содержит ряд
аннотаций для интеграции с клиентской частью

- `@Tag` – указывает имя пользовательского элемента веб-компонента HTML
- `@JsModule` – импортирует модуль JavaScript, содержащий клиентскую часть компонента

События клиентской части отправляемые как `CustomEvent` объекты JavaScript объявляются на стороне сервера с помощью
аннотации `@DomEvent` и `@EventData`. Также класс компонента имеет метод регистрации слушателя для этих событий. См.
вложенный клас `ThemeToggleThemeChangedEvent` и метод `addThemeChangeListener()` в [
`ThemeToggle.java`]({currentPath}?tab=ThemeToggle.java).

Файл [`theme-toggle.js`]({currentPath}?tab=theme-toggle.js) расположеный в папке `frontend/src/component/theme-switcher`
содержит клиентскую часть, которая является веб-компонентом, расширяющим класс Vaadin `Button`.

После создания клиентской и серверной частей компонента его можно использовать в представлениях, создав экземпляр его
класса Java и добавив его в родительский компонент.

Следующие шаги полностью интегрируют компонент в пользовательский интерфейс Jmix:

1. Создать XSD-схему для компонента (см. [`theme-toggle.xsd`]({currentPath}?tab=theme-toggle.xsd)).
2. Реализовать загрузчик для компонента (см. [`ThemeToggleLoader.java`]({currentPath}?tab=ThemeToggleLoader.java)).
3. Зарегистрировать компонент и его загрузчик в приложении (см. [
   `ThemeToggleRegistration.java`]({currentPath}?tab=ThemeToggleRegistration.java)).

После завершения этих шагов пользовательский компонент может быть использован в XML-дескрипторах представления. Смотрите
файл [`theme-toggle-usage.xml`]({currentPath}?tab=theme-toggle-usage.xml).

Подробнее о создании пользовательских компонентов см. в документации
Vaadin: [Создание компонентов](https://vaadin.com/docs/latest/create-ui/creating-components), [Использование интерфейсов Mixin](https://vaadin.com/docs/latest/create-ui/creating-components/mixins), [Использование событий и компонентов](https://vaadin.com/docs/latest/create-ui/creating-components/events).
