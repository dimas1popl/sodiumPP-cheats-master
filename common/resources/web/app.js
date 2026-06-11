import * as FallbackLoader from '/fallback-loader.js'
import { createComponent as createTargetEspComponent } from './components/visuals/TargetEsp.js';
import * as http from '/http.js'
import { getComponent } from '/components/Loader.js'
import * as events from '/events-service.js'
import { modules } from '/modules.js'

const { createApp, defineComponent, ref, computed, onMounted, onUnmounted } = await FallbackLoader.vue();

const main = getComponent('Main');
const keybindingsComponent = getComponent('KeyBindingScripts');

const App = defineComponent({
    components: {
        SwitchCheckbox: getComponent('common/SwitchCheckbox')
    },
    setup() {
        // Роуты для обычных модулей (исключая keybindings)
        const routes = {};
        for (let module of modules.all) {
            if (module.path && module.group !== 'keybindings') {
                routes['/' + module.path] = module;
            }
        }
        
        // Специальный маршрут для keybindings
        const KEYBINDINGS_PATH = '/keybindings';
        
        const path = ref(window.location.hash);
        
        // Определяем, показывать ли keybindings
        const isKeybindings = computed(() => {
            const currentPath = path.value.slice(1) || '/';
            return currentPath === KEYBINDINGS_PATH;
        });
        
        // Текущий модуль (не keybindings)
        const module = computed(() => {
            const route = routes[path.value.slice(1) || '/'];
            return route ?? null;
        });
        
        // Какой компонент показывать
        const view = computed(() => {
            if (isKeybindings.value) {
                return keybindingsComponent;
            } else if (module.value != null) {
                return module.value.componentRef;
            } else {
                return main;
            }
        });
        
        // Функции для навигации
        const goToKeybindings = () => {
            window.location.hash = KEYBINDINGS_PATH;
        };
        
        const goToMain = () => {
            window.location.hash = '/';
        };
        
        // Проверка, активен ли раздел
        const isActive = (section) => {
            if (section === 'keybindings') {
                return isKeybindings.value;
            }
            if (section === 'modules') {
                return !isKeybindings.value && module.value !== null;
            }
            if (section === 'main') {
                return !isKeybindings.value && module.value === null;
            }
            return false;
        };
        
        window.addEventListener('hashchange', () => {
            path.value = window.location.hash;
        });
        
        onMounted(() => {
            http.getText('/api/user').then(response => {
                document.title = response;
            });
        });
        
        return {
            path,
            module,
            view,
            isKeybindings,
            goToKeybindings,
            goToMain,
            isActive
        };
    }
});

const app = createApp(App);
app.mount('#vue-app');