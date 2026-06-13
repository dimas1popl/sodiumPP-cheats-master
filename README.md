# Dimsteams SodiumPP

## Предупреждение

Если вы используете кнопку загрузки на GitHub, репозиторий не будет работать, так как он использует подмодули git.

Чтобы загрузить репозиторий вместе с подмодулями, используйте команду ниже:

`git clone --recurse-submodules https://github.com/dimsteams/sodiumpp.git`

## Сборка

Чтобы собрать мод самостоятельно, перейдите в папку Forge или Fabric и выполните `gradlew build`.
Требуется JDK 25.

## Отладка / настройка веб-приложения

Скачайте репозиторий (или только каталог `/common/resources/web`), и добавьте аргумент JVM в лаунчере Minecraft, например:

```bat
-Dsodiumpp.web.dir=C:\полный\путь\к\web\каталогу
```

Теперь локальный веб-сайт использует статические файлы из этого каталога вместо ресурсов мода.

## Примеры кода :-

### Добавление модуля в мод :-

#### Шаг 1: Создайте ваш главный файл класса.

Здесь будет находиться большая часть кода вашего модуля.

Сначала перейдите в папку modules, расположенную в [`common/java/com/dimsteams/sodiumpp/modules`](common/java/com/dimsteams/sodiumpp/modules).
Выберите папку, которая лучше всего подходит для вашего типа модуля.
Далее мы будем ссылаться на теоретический модуль под названием "AutoPearl", однако вы должны следовать тем же соглашениям об именовании.

Создайте новый файл: [`AutoPearl.java`](common/java/com/dimsteams/sodiumpp/modules).

Класс должен быть в таком формате:

```java
package com.dimsteams.sodiumpp.modules.automation;
import com.dimsteams.sodiumpp.modules.Module;

public class AutoPearl implements Module {
    public static final AutoPearl instance = new AutoPearl();
    private AutoPearl() {

    }
}
```
Убедитесь, что добавили строку:
`package com.dimsteams.sodiumpp.modules.<папка>;`  
`<папка>` должна соответствовать месту, где находится ваш файл класса внутри папки [`modules`](./common/java/com/dimsteams/sodiumpp/modules).
В этом примере мы помещаем его в automation:
`package com.dimsteams.sodiumpp.modules.automation;`

#### Шаг 2: Зарегистрируйте ваш модуль в моде.

Для целей данного объяснения предполагается, что ваш модуль называется "AutoPearl".
Перейдите в [`Modules.java`](./common/java/com/dimsteams/sodiumpp/modules/Modules.java)
Добавьте ваш модуль в следующую функцию

```java
public static void register() {
    register(AutoPearl.instance);
}
```

Убедитесь, что разместили его в правильном порядке, модули инициализируются в том порядке, в котором они перечислены здесь. Сюда входит порядок добавления их событий в EventsApi.

>[!ПРИМЕЧАНИЕ]
> Это относится только к старым модулям, которые не указывают свою собственную важность. Важность по умолчанию для прикрепленных событий равна `0`.

#### Шаг 3: Добавьте ваш конфигурационный класс

Перейдите в [`common/java/com/dimsteams/sodiumpp/configs`](./common/java/com/dimsteams/sodiumpp/configs)

создайте новый файл `AutoPearlConfig.java`

```java
package com.dimsteams.sodiumpp.configs;

public class AutoPearlConfig extends ModuleConfig implements Sanitizable {

    private AutoPearlConfig() {

    }

    @Override
    public void sanitize() {
        return;
    }
}
```

добавьте вашу валидацию конфигурации внутрь метода `sanitize()`. Здесь вы добавите ограничения для ваших полей, например, ограничение диапазона значения.
Если ваш API будет доступен из скриптов, настоятельно рекомендуется включать валидацию для любых полей.

>[!ПРИМЕЧАНИЕ]
> Если ваш модуль не требует валидации, вы можете пропустить `implements Sanitizable` и переопределенную функцию `public void Sanitize()`. В этом случае также пропустите добавление валидации в [`Root.java`](#добавление-модуля-в-мод).
>
> Если ваш модуль не требует включения/выключения, вы также можете пропустить `extends ModuleConfig`. Это также означает, что вы не можете использовать булеву переменную `enabled` или унаследованные функции `isEnabled()`. Имейте это в виду при создании веб-сайта / кода для него.

Вы также можете добавить любые другие функции или значения для изменения.
Например:

```java
package com.dimsteams.sodiumpp.configs;

public class AutoPearlConfig extends ModuleConfig implements Sanitizable {
    public boolean bl1;
    public double db1;

    public AutoPearlConfig() {
        bl1 = true;
        db1 = 100;
    }

    @Override
    public void sanitize() {
        return;
    }
}
```

И так далее. Они должны быть публичными, вы также можете использовать методы внутри, однако методы не будут работать с веб-API.
Этот API напрямую изменяет поля.

Любые присваивания в конструкторе класса используются только в том случае, если ваша сохраненная конфигурация в моде **не имеет соответствующих значений**.
Это означает, что эти присваивания действуют как **конфигурация по умолчанию** вашего модуля до того, как пользователь внесет какие-либо изменения.

Пример минимальной конфигурации, которая не требует валидации или переменной включения:

```java
    public class AutoPearlConfig {}
```

**Как получить доступ к конфигурации**  
Используйте этот шаблон для доступа к переменным конфигурации:

```java
import com.dimsteams.sodiumpp.configs.AutoPearlConfig;
import com.dimsteams.sodiumpp.configs.ConfigStore;

AutoPearlConfig config = ConfigStore.instance.getConfig().autoPearlConfig;

// Доступ к переменным обычным способом
if(!config.enabled)return;
```

\
\
**Затем перейдите в [`Config.java`](./common/java/com/dimsteams/sodiumpp/configs/Config.java), расположенный в том же каталоге.**

Добавьте вашу конфигурацию в класс

```java
    public AutoPearlConfig autoPearlConfig = new AutoPearlConfig();
```
\
**ЕСЛИ ваш модуль использует метод `sanitize()`, убедитесь, что добавили эти строки в `public void sanitize()`**

```
    autoPearlConfig.sanitize();
```

#### Шаг 4: Добавление API модуля для работы веб-сайта.

Перейдите в [`common/java/com/dimsteams/sodiumpp/scripting/modules/`](./common/java/com/dimsteams/sodiumpp/scripting/modules/)
создайте новый файл `AutoPearlApi.java`

добавьте эти строки

```java
package com.dimsteams.sodiumpp.scripting.modules;

import com.dimsteams.sodiumpp.configs.AutoPearlConfig;
import com.dimsteams.sodiumpp.configs.ConfigStore;

public class AutoPearlApi extends ModuleApi<AutoPearlConfig> {

    @Override
    protected AutoPearlConfig getConfig() {
        return ConfigStore.instance.getConfig().autoPearlConfig;
    }
}
```

#### Шаг 5: Разрешите доступ к API из средств скриптинга

(относится к скриптингу со стороны пользователя)

Этот шаг **НЕОБЯЗАТЕЛЕН** и не требуется для функционирования модуля.
Если вы хотите, чтобы к конфигурации вашего модуля можно было получить доступ из скриптинга.
Тем не менее, рекомендуется выполнить этот шаг.

Перейдите в [`common/java/com/dimsteams/sodiumpp/scripting/Root.java`](./common/java/com/dimsteams/sodiumpp/scripting/Root.java)

Добавьте следующие строки внутри класса `Root`.

```java
public static AutoPearlApi autoPearl = new AutoPearlApi();
```

#### Шаг 6

Добавление вашего модуля на веб-сайт

Перейдите в [`common/resources/web/modules.js`](./common/resources/web/modules.js)

Добавьте ваш модуль под `строкой 21` в следующем формате:

```javascript
module({
    group: 'названиеГруппы',
    name: 'Отображаемое Имя',
    component: 'имяКомпонента',
    path: 'имя-класса',
    tags: ['поисковые', 'термины', 'идентификаторы']
});
```
Поисковые термины (теги) должны отражать функцию модуля.
Убедитесь, что вы добавили ваш модуль в правильный раздел вместе с остальными его группы.
Вот следующие группы, которые можно использовать:

```javascript
'automation'
'esp'
'hacks'
'visuals'
'scripting'
'utility'
```
\
\
В нашем примере мы используем `'automation'`, измените это в соответствии с типом вашего модуля:  
Пример:

```javascript
module({
    group: 'automation',
    name: 'Auto Pearl',
    component: 'AutoPearl',
    path: 'auto-pearl',
    tags: ['pearl', 'automation', 'throw']
});
```

теперь перейдите в [`common/resources/web/components`](./common/resources/web/components)  
далее перейдите в соответствующую папку для вашего модуля из доступных папок. Это сопоставляется с используемыми группами, поэтому убедитесь, что вы используете ту же папку.

**Создайте 2 новых файла:**

* AutoPearl.js
* AutoPearl.html

внутри `JavaScript` файла добавьте следующее:

```javascript
import { createSimpleComponent } from '/components/SimpleModule.js';

export function createComponent(template) {
    return createSimpleComponent('/api/auto-pearl', template);
}

```

если вы используете какие-либо новые компоненты, перечисленные в [`common/resources/web/components.js`](./common/resources/web/components.js) в вашем html, убедитесь, что включили их в ваш javascript файл

например:

```javascript
import { createSimpleComponent } from '/components/SimpleModule.js'

export function createComponent(template) {
    return createSimpleComponent('/api/auto-pearl', template, {
        components: ['CodeBlock', 'ColorBox']
    });
}
```

##### Как создать ваш html файл

Это руководство сосредоточится на работе.  
По вопросам стиля и другого форматирования / использования компонентов смотрите html других уже реализованных модулей.

**ваш базовый html файл должен выглядеть так:**

```html
<div class="module-main" v-if="config">

</div>
```

все компоненты можно найти по адресу `common/resources/web/components/common`
