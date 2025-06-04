
Этот пример демонстрирует, как изолировать сущности при их отправке с экрана в пользовательский сервис. Для получения дополнительной информации см. раздел документации [Работа с сущностями в UI]({docsBaseUrl}flow-ui/data/entities-in-ui.html).

Экраны списка и деталей в этом примере позволяют пользователям управлять сущностью `Contract`, которая имеет атрибут-перечисление `status` с возможными значениями `DRAFT`, `APPROVED`, `REJECTED`.

Кнопки **Approve**, **Revise** и **Reject** на экране деталей вызывают бин `ContractService`, передавая ему редактируемый `Contract` и новый статус. Сервис обновляет статус в экземпляре сущности и сохраняет изменения.

Стандартный процесс сохранения экрана, инициируемый кнопкой **Save**, перехватывается методом-обработчиком `saveDelegate()`. Он также вызывает бин `ContractService`. Обратите внимание, что обработчик вызывается только если сущность была изменена с момента последнего сохранения.

Детали реализации:

- [contract-detail-view.xml]({currentPath}?tab=contract-detail-view.xml)
  - `saveAction` имеет тип `detail_save`. Поэтому действие не закрывает экран после нажатия **Save**.
- [ContractDetailView.java]({currentPath}?tab=ContractDetailView.java)
  - Метод `changeContractStatus()` создает копию редактируемой сущности и отправляет эту копию в сервис. Возвращенный экземпляр помещается в `DataContext`. Помещенный экземпляр игнорируется, так как он используется только внутренней логикой экрана.
  - Обработчик `saveDelegate()` вызывается из `DataContext` при нажатии пользователем кнопки **Save**. Он также создает копию редактируемой сущности и отправляет её в сервис для сохранения. Сохраненный экземпляр возвращается из сервиса и обработчика. Он помещается в `DataContext` автоматически после выхода из обработчика.
- [ContractService.java]({currentPath}?tab=ContractService.java)
  - Метод `changeStatus()` изменяет статус экземпляра сущности и отправляет его в `DataManager`. Обновленный экземпляр затем возвращается для обновления состояния экрана. Если в методе сервиса возникает исключение, состояние сущности, представленной на экране, остается неизменным, так как сервис работает с копией сущности.
- [ContractListView.java]({currentPath}?tab=ContractListView.java) defines "after close" handlers for `createAction` and `editAction` to always refresh the entities list after closing the detail window. This is required because data might be modified while the detail view was open.
- [ContractListView.java]({currentPath}?tab=ContractListView.java) содержит обработчики "after close" для `createAction` и `editAction`, чтобы всегда обновлять список сущностей после закрытия экрана деталей. Это необходимо, потому что данные могли быть изменены экраном деталей, когда он был открыт.