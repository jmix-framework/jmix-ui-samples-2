package io.jmix.uisamples.view.flowui.components.richtexteditor.simple;

import io.jmix.flowui.component.richtexteditor.RichTextEditor;
import io.jmix.flowui.view.*;

@ViewController("rich-text-editor-simple")
@ViewDescriptor("rich-text-editor-simple.xml")
public class RichTextEditorSimpleSample extends StandardView {

    @ViewComponent
    protected RichTextEditor richTextEditor;

    @Subscribe
    protected void onInit(InitEvent event) {
        richTextEditor.setValue("""
                <h1>H1</h1>
                <h2>H2</h2>
                <h3>H3</h3>
                <ul>
                <li><b>Bold</b></li>
                <li><i>Italic</i></li>
                <li><u>Underline</u></li>
                </ul>
                <ol>
                <li><s>Strikethrough</s></li>
                <li>Sub<sub>script</sub></li>
                <li>Super<sup>script</sup></li>
                <li><a href="https://www.jmix.io/" rel="nofollow">Link</a>
                </li>
                </ol>
                <pre>&lt;code-block/&gt;</pre>
                <blockquote>Blockquote</blockquote>
                """);
    }
}
