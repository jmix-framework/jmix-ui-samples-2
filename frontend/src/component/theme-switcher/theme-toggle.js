import {html} from "@polymer/polymer";
import {Button} from "@vaadin/button";
import {css, registerStyles} from "@vaadin/vaadin-themable-mixin";

/**
 * Use registerStyles instead of the `<style>` tag to make sure
 * that this CSS will override core styles of `vaadin-button`.
 */
registerStyles(
    'theme-toggle',
    css`
      :host {
        background: transparent;

        color: var(--lumo-text-color);
      }
    `,
    {moduleId: 'theme-toggle-styles'},
);

class ThemeToggle extends Button {

    static get is() {
        return 'theme-toggle';
    }

    static get template() {
        return html`
            <div class="vaadin-button-container">
                <span part="prefix" aria-hidden="true">
                  <slot name="prefix"></slot>
                </span>
                <span part="label">
                    <slot></slot>
                </span>
            </div>

            <slot name="tooltip"></slot>
        `;
    }

    static get properties() {
        return {
            ariaLabel: {
                type: String,
                value: 'Theme toggle',
                reflectToAttribute: true,
            },

            storageKey: {
                type: String,
                value: 'jmix.flowui.theme',
                observer: '_onStorageKeyChanged'
            }
        };
    }

    constructor() {
        super();

        this.addEventListener('click', () => this.toggleTheme());
    }

    /** @protected */
    ready() {
        super.ready();

        this.applyStorageTheme();
    }

    applyStorageTheme() {
        let storageTheme = this.getStorageTheme();
        let currentTheme = this.getCurrentTheme();
        if (storageTheme && currentTheme !== storageTheme) {
            this.applyTheme(storageTheme);
        }
    }

    getStorageTheme() {
        return localStorage.getItem(this.storageKey);
    }

    getCurrentTheme() {
        return document.documentElement.getAttribute("theme");
    }

    toggleTheme() {
        const theme = this.getCurrentTheme();
        this.applyTheme(theme === "dark" ? "" : "dark");
    }

    applyTheme(theme) {
        document.documentElement.setAttribute("theme", theme);
        localStorage.setItem(this.storageKey, theme);
    }

    /** @protected */
    _onStorageKeyChanged(storageKey, oldStorageKey) {
        const theme = localStorage.getItem(oldStorageKey);
        localStorage.removeItem(oldStorageKey);

        if (theme) {
            localStorage.setItem(storageKey, theme);
        }
    }
}

customElements.define(ThemeToggle.is, ThemeToggle);

export {ThemeToggle};