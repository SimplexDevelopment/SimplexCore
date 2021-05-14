# SimplexCore

<p>SimplexCore is a plugin created by 
<a href=https://www.simplexdev.app>Simplex Development Group</a>.</p>
<p>This plugin is an API and framework plugin designed to make plugin development easier, and more user friendly.</p>
<p>SimplexCore vastly reduces boilerplate by creating autonomous functions which do most of the tedious work for you.
Registering commands is as simple as creating classes annotated with the @CommandInfo annotation, and extending SimplexCommand, which automatically registers with the spigot framework. This removes the need of using the plugin.yml to define commands and permissions.
Creating items uses a worker class which is a recursive object that builds an item and outputs the result.
SimplexListener subclasses are automatically registered upon new class initialization.</p>
<p>Everything is still in the process of being JavaDocced, so if there is no javadocs for the class or method you are trying to use, one will be added in a future update.</p>
