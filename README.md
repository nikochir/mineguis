# mineguis #
## description ##
* minecraft graphical user interface via inventories and items;
* provides an interface to create menu panels;
* implements plugin for menu facilities;
## languages ##
code | usefor
---- | ------
java | source
yml  | config
xml  | config
## environment ##
* this is set up primarly for portable vscode;
    * java configuration in the workspace file needs to be changed
    because it uses local path of the machiene that developer used;
* building system is targeted on maven;
* theoretically, it can run with maven in the terminal;
## features ##
* since version 003 we have a plugin that manages unique gui elements and user data;
    * primary objects: items and menus;
    * every object is uniquely defined by it's signature;
* all specific things are stored on the client side;
    * user class has permission attachments and a short usage history;
* menu static and dynamic building;
    * create a menu with some items right in the source code;
    * write all required menus and items in the config.yml file;
* every menu has unlimited size;
    * we can specify the size in rows but if we minecraft restricts it up to 6 rows;
    * menu has prev/next links like in a 2-linked list;
        * when we hit over 54 cells, it creates next pages recursively untill it gets enough space;
* items are universal;
    * any item has it's own command;
        * can specify as many commands as we want and bind items to them;
        * every item also handles arguments so it creates association between an action and an item;