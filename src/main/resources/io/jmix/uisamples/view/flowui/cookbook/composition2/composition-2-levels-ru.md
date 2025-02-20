Этот пример демонстрирует двухуровневую композицию сущностей `Airport`, `Terminal` и `MeetingPoint`: аэропорт содержит коллекцию терминалов, которые, в свою очередь, содержат коллекции точек встречи.

Терминалы и точки встречи сохраняются в базу данных только вместе с владеющим аэропортом, когда вы нажимаете кнопку **OK** на экране деталей аэропорта. Если вы изменяете терминал или точку встречи и нажимаете **OK** на их экране деталей, но затем нажимаете **Cancel** на экране деталей аэропорта, изменения не сохранятся.

Детали реализации:

- [Airport.java]({currentPath}?tab=Airport.java)
  - Атрибут `Airport.terminals` аннотирован с помощью `@Composition`.

- [Terminal.java]({currentPath}?tab=Terminal.java)
  - Атрибут `Terminal.meetingPoints` аннотирован с помощью `@Composition`.

- [airport-detail-view.xml]({currentPath}?tab=airport-detail-view.xml)
  - Фетч-план для загрузки `Airport` включает атрибут `Airport.terminals`.
  - Определен вложенный контейнер данных для атрибута `Airport.terminals`.
  - Таблица данных, отображающая терминалы, подключена к этому контейнеру данных.
  - Действия `create` и `edit` таблицы данных терминалов открывают экран деталей терминала в виде диалога (см. `<property name="openMode" value="DIALOG"/>`).

- [terminal-detail-view.xml]({currentPath}?tab=terminal-detail-view.xml)
  - Фетч-план для загрузки `Terminal` включает атрибут `Terminal.meetingPoints`.
  - Определен вложенный контейнер данных для атрибута `Terminal.meetingPoints`.
  - Таблица данных, отображающая точки встречи, подключена к этому контейнеру данных.
  - Действия `create` и `edit` таблицы данных точек встречи открывают экран деталей точки встречи в виде диалога (см. `<property name="openMode" value="DIALOG"/>`).

Использование нескольких вложенных диалоговых окон может быть неудобным для пользователей. Следующие примеры иллюстрируют, как минимизировать вложенность при редактировании той же структуры данных:

- [Master-detail вверху композиции]({contextPath}/sample/composition-master-detail-1)
- [Master-detail внизу композиции]({contextPath}/sample/composition-master-detail-2)
- [Использование inline-редактирования]({contextPath}/sample/composition-inline-edit)