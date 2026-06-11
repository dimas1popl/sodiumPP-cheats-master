import * as http from '/http.js'
import { getComponent } from '/components/Loader.js'

const modules = {
    all: [],
    automation: {},
    esp: {},
    visuals: {},
    utility: {}
};

const module = (params) => {
    modules.all.push(params);

    params.componentRef = getComponent(`${params.group}/${params.component}`);
    modules[params.group][params.component] = params;
};

// Automation Modules =================


module({
    group: 'automation',
    name: 'Auto Bucket',
    component: 'AutoBucket',
    path: 'auto-bucket',
    tags: ['auto', 'bucket', 'mlg', 'nofall', 'no', 'fall']
});
module({
    group: 'automation',
    name: 'Auto Fish',
    component: 'AutoFish',
    path: 'auto-fish',
    tags: ['auto', 'fish', 'fishing']
});
module({
    group: 'automation',
    name: 'Auto Totem',
    component: 'AutoTotem',
    path: 'auto-totem',
    tags: ['auto', 'totem']
});
module({
    group: 'automation',
    name: 'Auto Drop',
    component: 'AutoDrop',
    path: 'auto-drop',
    tags: ['auto', 'drop', 'inventory']
});

module({
    group: 'automation',
    name: 'Auto Eat',
    component: 'AutoEat',
    path: 'auto-eat',
    tags: ['auto', 'eat']
});

module({
    group: 'automation',
    name: 'Auto Craft',
    component: 'AutoCraft',
    path: 'auto-craft',
    tags: ['auto', 'craft']
});


module({
    group: 'automation',
    name: 'Auto Hotbar',
    component: 'AutoHotbar',
    path: 'auto-hotbar',
    tags: ['auto', 'hotbar']
});
module({
    group: 'automation',
    name: 'Auto Tool',
    component: 'AutoTool',
    path: 'auto-tool',
    tags: ['auto', 'tool']
});


// ESP modules ======================

module({
    group: 'visuals',
    name: 'Target ESP',
    component: 'TargetEsp',
    path: 'target-esp',
    tags: ['esp', 'player']
});
module({
    group: 'esp',
    name: 'Projectile Path',
    component: 'ProjectilePath',
    path: 'projectile-path',
    tags: ['projectile', 'path', 'ender', 'pearl']
});
module({
    group: 'esp',
    name: 'Light Level',
    component: 'LightLevel',
    path: 'light-level',
    tags: ['light', 'level', 'mob', 'spawn']
});
module({
    group: 'esp',
    name: 'End City Chunks',
    component: 'EndCityChunks',
    path: 'end-city-chunks',
    tags: ['end', 'city', 'cities', 'chunks']
});
module({
    group: 'esp',
    name: 'Entity Owner',
    component: 'EntityOwner',
    path: 'entity-owner',
    tags: ['entity', 'owner']
});
module({
    group: 'esp',
    name: 'Free Cam',
    component: 'FreeCam',
    path: 'freecam',
    tags: ['freecam', 'camera']
});
module({
    group: 'esp',
    name: 'New Chunks',
    component: 'NewChunks',
    path: 'new-chunks',
    tags: ['new', 'chunks']
});
module({
    group: 'esp',
    name: 'Entity Titles',
    component: 'EntityTitle',
    path: 'entity-titles',
    tags: ['entity', 'title', 'health']
});


// Visuals Modules ============================

module({
    group: 'visuals',
    name: 'Full Bright',
    component: 'FullBright',
    path: 'full-bright',
    tags: ['full', 'bright', 'night', 'vision']
});
module({
    group: 'visuals',
    name: 'Armor Overlay',
    component: 'ArmorOverlay',
    path: 'armor-overlay',
    tags: ['armor', 'overlay']
});
module({
    group: 'visuals',
    name: 'Shulker Tooltip',
    component: 'ShulkerTooltip',
    path: 'shulker-tooltip',
    tags: ['shulker', 'tooltip']
});
module({
    group: 'visuals',
    name: 'Advanced Tooltips',
    component: 'AdvancedTooltips',
    path: 'adv-tooltips',
    tags: ['advanced', 'tooltips']
});
module({
    group: 'visuals',
    name: 'Exploration Mini Map',
    component: 'ExplorationMiniMap',
    path: 'exploration-mini-map',
    tags: ['exploration', 'minimap']
});
module({
    group: 'visuals',
    name: 'Death Coordinates',
    component: 'DeathCoordinates',
    path: 'death-coordinates',
    tags: ['death', 'coordinates']
});
module({
    group: 'visuals',
    name: 'No Fog',
    component: 'Fog',
    path: 'no-fog',
    tags: ['fog']
});
module({
    group: 'visuals',
    name: 'Chunks',
    component: 'Chunks',
    path: 'chunks',
    tags: ['chunks', 'distance']
});
module({
    group: 'visuals',
    name: 'Status Effects',
    component: 'StatusEffects',
    path: 'status-effects',
    tags: ['status', 'effects']
});
module({
    group: 'visuals',
    name: 'Zoom',
    component: 'Zoom',
    path: 'zoom',
    tags: ['zoom']
});
module({
    group: 'visuals',
    name: 'Performance',
    component: 'Performance',
    path: 'performance',
    tags: ['performance', 'fps']
});
module({
    group: 'visuals',
    name: 'World Markers',
    component: 'WorldMarkers',
    path: 'world-markers',
    tags: ['world', 'markers']
});
module({
    group: 'visuals',
    name: 'Hurt Bobbing',
    component: 'BobHurt',
    path: 'bob-hurt',
    tags: ['nohurtcam', 'bobhurt']
});
module({
    group: 'visuals',
    name: 'Logout Spots',
    component: 'LogoutSpots',
    path: 'logout-spots',
    tags: ['logout', 'spots']
});
module({
    group: 'visuals',
    name: 'Block Entity',
    component: 'BlockEntityDistance',
    path: 'block-entity',
    tags: ['block', 'entity', 'chest', 'render']
});
module({
    group: 'visuals',
    name: 'Hands View',
    component: 'HandsView',
    path: 'hands-view',
    tags: ['hands', 'view', 'render']
});



module({
    group: 'utility',
    name: 'World Download',
    component: 'WorldDownload',
    path: 'world-download',
    tags: ['world', 'download']
});
module({
    group: 'utility',
    name: 'Key Bindings',
    component: 'KeyBindingScripts',
    path: 'key-bindings',
    tags: ['key', 'bindings', 'utility']
});

module({
    group: 'utility',
    name: 'Reset',
    component: 'Reset',
    path: 'reset',
    tags: ['reset', 'config'],
    dangerous: true
});


export { modules }